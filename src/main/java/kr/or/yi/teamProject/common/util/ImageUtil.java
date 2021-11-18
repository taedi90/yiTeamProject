package kr.or.yi.teamProject.common.util;

import com.sksamuel.scrimage.ImmutableImage;
import com.sksamuel.scrimage.nio.*;
import kr.or.yi.teamProject.common.enums.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 *
 * todo : 파일 동일이름 존재 확인 유틸
 */
@Slf4j
public class ImageUtil {

    public static final String UPLOAD_BASE_PATH = path(); //이미지 업로드 베이스 경로

    private String[] exts = {"jpg", "jpeg", "gif", "png"}; //지원 확장자

    private int[] preset = new int[] {350, 130, 80}; //프리셋


    //베이스 경로 설정
    private static String path(){

        String os = System.getProperty("os.name");  //서버 운영체제 (Mac OS X, Windows 10, ...)
        log.info("Server OS - " + os);

        //class path 를 url 형태로 얻음 "file:/C:/Users/Taedi/IdeaProjects/%ed%85%8c~~~/webapps/myapp/WEB-INF/classes/"
        //URL encodedPath = this.getClass().getResource("/"); //for non static method
        //URL encodedPath = ImageUtil.class.getClass().getResource("/");
        URL encodedPath = Thread.currentThread().getContextClassLoader().getResource("/");

        log.info(encodedPath.toString());

        //url 형식 디코딩 "/C:/Users/Taedi/IdeaProjects/테스트~~~/webapps/myapp/WEB-INF/classes/"
        String decodedPath = null;
        try {
            decodedPath = URLDecoder.decode(encodedPath.getFile(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //운영체제가 윈도우일 경우 경로 최상단의 '/' 제거
        if(os.contains("Windows")){
            if (decodedPath.startsWith("/")) {
                decodedPath = decodedPath.replaceFirst("/", "");
            }
        }


        //프로젝트가 배포 된 경로
        String deployPath = Paths.get(decodedPath).getParent().getParent().getParent().toString();
        log.info("deploying path - " + deployPath);


        //이미지 업로드 경로 지정(프로젝트 배포 경로에 따라 판단)
        String uploadPath;
        if(deployPath.equals("webapps")){
            //배포 경로가 외부 서버일 경우
            uploadPath = deployPath + File.separator +"yiTeamProjectStorage" + File.separator + "upload";
        }else {
            //개발 환경일 경우(wtpwebapps, target, ...)
            uploadPath = Paths.get(decodedPath).getParent().getParent().toString() + File.separator + "resources" + File.separator + "upload";

        }


        //저장 경로가 없을 경우 생성시도
        File folder = new File(uploadPath);
        if(!folder.exists()) {
            try{
                folder.mkdir();
                log.info("mkDir - " + uploadPath);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        log.info("uploadPath - " + uploadPath);
        return uploadPath;
    }

    //경로 체크(없을 경우 생성)
    public boolean checkPath(String uploadPath) {
        File folder = new File(uploadPath);
        if(!folder.exists()) {
            try{
                folder.mkdir();
                log.info("mkDir - " + uploadPath);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    //썸네일 업로드
    public boolean thumbResize(MultipartFile multipartFile, String subPath) {

        //썸네일은 무조건 png, 정방형

        //확장자 확인
        String filename = multipartFile.getOriginalFilename();
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

        log.info(ext);

        //지원하지 않는 확장자 리턴
        if(!Arrays.stream(exts).anyMatch(ext::equals)){
            return false;
        }

        //업로드 경로 생성
        String uploadPath = UPLOAD_BASE_PATH + File.separator + subPath;
        if(!checkPath(uploadPath)){
            return false;
        }

        //파일 저장
        File originFile = new File(uploadPath, "origin." + ext);
        try {
            multipartFile.transferTo(originFile);
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }

        try {
            ImmutableImage originalImage = ImmutableImage.loader().fromFile(originFile);

            int min = Math.min(originalImage.height, originalImage.width);
            ImmutableImage resizeImage = originalImage.resizeTo(min, min);


            PngWriter writer = new PngWriter().withCompression(100);

            originalImage.output(writer, new File(uploadPath + File.separator + "thumb.png"));

            //리사이즈
            for (int pre : preset) {
                resizeImage = resizeImage.scaleTo(pre, pre);
                resizeImage.output(writer, new File(uploadPath + File.separator + "thumb_" + pre + ".png"));
            }


        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    //일반 업로드
    public List<Map<String,Object>> uploadForMultipart(MultipartFile[] MultipartFiles, String subPath){

        //success, originalFilename, comment, path

        List<Map<String,Object>> list = new ArrayList<>();

        //업로드 경로 생성
        String uploadPath = UPLOAD_BASE_PATH + File.separator + subPath;
        if(!checkPath(uploadPath)){
            return null;
        }

        for (MultipartFile multipartFile : MultipartFiles) {

            HashMap<String,Object> map = new HashMap<>();

            //확장자 확인
            String filename = multipartFile.getOriginalFilename();
            //String name = filename.substring(0, filename.lastIndexOf("."));
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

            map.put("originalFileName", filename);

            //지원하지 않는 확장자 패스
            if(!Arrays.stream(exts).anyMatch(ext::equals)){
                map.put("success", false);
                map.put("comment", "지원하지 않는 확장자 오류");
                list.add(map);
                continue;
            }

            RandomStringCreateUtil rscu = new RandomStringCreateUtil();
            rscu.setSize(30);
            //String newName = name + "_" + rscu.getSecureRand();
            String newName = rscu.getSecureRand();;

            //파일 저장
            File originFile = new File(uploadPath, newName + "." + ext);
            try {
                multipartFile.transferTo(originFile);
            } catch (Exception e) {
                e.getStackTrace();
                map.put("success", false);
                map.put("comment", "파일 저장 실패");
                list.add(map);
                continue;
            }


//            try {
//                map.put("success", true);
//                map.put("comment", "업로드 성공");
//                map.put("path", "upload/" + subPath + "/" + URLEncoder.encode(newName,"UTF-8") + "." + ext);
//                list.add(map);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }

            map.put("success", true);
            map.put("comment", "업로드 성공");
            map.put("path", "upload/" + subPath + "/" + newName + "." + ext);
            list.add(map);
        }

        return list;
    }

    //리사이즈 업로드
    public String resizeForMultipart(MultipartFile multipartFile, String savePath) {

        //썸네일은 무조건 png 저장

        //확장자 확인
        String filename = multipartFile.getOriginalFilename();
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

        log.info(ext);

        //지원하지 않는 확장자 리턴
        if(!Arrays.stream(exts).anyMatch(ext::equals)){
            return null;
        }

        //파일 저장
        File saveFile = new File(UPLOAD_BASE_PATH, filename);
        try {
            multipartFile.transferTo(saveFile);
        } catch (Exception e) {

        }





        //gif는 별도 취급
        //jpg, png 등은


        return null;
    }



    public CommonResult upload(File originalImage) throws IOException {
        //png, jpeg, gif, tiff, webp
        ImmutableImage image = ImmutableImage.loader().fromFile(originalImage);


        image.output(PngWriter.NoCompression, new File(""));

        return null;
    }

    public String getThumb() {
        return null;
    }



    public CommonResult resizeGif(File originalImage, int height, int width, String savePath, boolean ratio) throws IOException {

        //gif 읽기
        AnimatedGif gif = AnimatedGifReader.read(ImageSource.of(originalImage));

        //작성하기
        StreamingGifWriter writer = new StreamingGifWriter(gif.getDelay(0), true); //딜레이(프레임간 차등 딜레이 불가), 반복여부

        StreamingGifWriter.GifStream resizedGif = writer.prepareStream(UPLOAD_BASE_PATH + File.separator + "result.gif", BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < gif.getFrameCount(); i++){

            //ratio 확인
            //자를부분 (가로 세로 확인)
            //scaleToWidth or scaleToHeight -> resize


            resizedGif.writeFrame(
                    //gif.getFrame(i).resizeTo(200, 200)
                    gif.getFrame(i).scaleTo(200,200)
            );
        }
        //resizedGif.finish();

        return null;

    }



//                BufferedImage resizeImg = Scalr.resize(img, Scalr.Method.BALANCED, 300, 300);
//
//                File thumbFile =  new File(ImageUtil.UPLOAD_PATH + File.separator + "resize.gif");
//                ImageIO.write(resizeImg, "gif", thumbFile);
//
//
//
//                ImageIcon gif = new ImageIcon(ImageIO.read(multipartFile.getInputStream()));
//                gif.setImage(gif.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
//
//
//                // Create a buffered image with transparency
//                BufferedImage bimage = new BufferedImage(gif.getImage().getWidth(null), gif.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
//
//                // Draw the image on to the buffered image
//                Graphics2D bGr = bimage.createGraphics();
//                bGr.drawImage(gif.getImage(), 0, 0, null);
//                bGr.dispose();
//
//
//                File thumbFile2 =  new File(ImageUtil.UPLOAD_PATH + File.separator + "rrrr.gif");
//                ImageIO.write(bimage, "gif", thumbFile2);
//
//
//                File thumbFile3 =  new File(ImageUtil.UPLOAD_PATH + File.separator +multipartFile.getOriginalFilename());
//                URL gifThumb;
}
