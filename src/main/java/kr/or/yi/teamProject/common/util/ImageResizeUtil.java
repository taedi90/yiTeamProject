package kr.or.yi.teamProject.common.util;

import lombok.Builder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * 이미지 파일을 리사이즈 해주는 유틸입니다.
 *
 * 결과 : 1 - 성공, 0 - 일부 또는 전부 실패
 *
 * @example
 * ImageResizeUtil iru = ImageResizeUtil.builder().
 *
 * @author taedi
 */


public class ImageResizeUtil {

    private int[] preset = new int[] {300, 500, 800};

    // 변환 포맷
    private String imgFormat = "png";

    // 변환 이름 {transformName}_{size}.{imgFormat}
    private String transformName = "result";


    public void setPreset(int[] preset) {
        this.preset = preset;
    }

    public void setImgFormat(String imgFormat) {
        this.imgFormat = imgFormat;
    }

    public void setTransformName(String transformName) {
        this.transformName = transformName;
    }

    // 원본 이미지 사이즈
    private int imageWidth;
    private int imageHeight;

    private Image image;

    public int resize(String imgOriginalPath, String imgTargetPath) {

        try {
            // 원본 이미지 가져오기
//            image = ImageIO.read(new File(imgOriginalPath));

            image = new ImageIcon(imgOriginalPath).getImage();

            // 원본 이미지 사이즈 가져오기
            imageWidth = image.getWidth(null);
            imageHeight = image.getHeight(null);

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }


        ArrayList<Integer> arr = new ArrayList();
        for (int size :preset) {
            int res = resizeSub(image, size, imgTargetPath);
            arr.add(res);
        }

        if(arr.contains(0)) {
            return 0;
        }

        return 1;

    }

    private int resizeSub(Image image, int size, String imgTargetPath) {

        try{
            // 리사이즈 크기
            int newWidth = size;
            int newHeight = size;

            // 저장 될 경로 및 파일 이름
            StringBuilder resizeFullPath = new StringBuilder();
            resizeFullPath.append(imgTargetPath);
            resizeFullPath.append(File.separator);
            resizeFullPath.append(transformName);
            resizeFullPath.append("_");
            resizeFullPath.append(size);
            resizeFullPath.append(".");
            resizeFullPath.append(imgFormat);

            // 이미지 리사이즈
            // Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
            // Image.SCALE_FAST : 이미지 부드러움보다 속도 우선
            // Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
            // Image.SCALE_SMOOTH : 속도보다 이미지 부드러움을 우선
            // Image.SCALE_AREA_AVERAGING : 평균 알고리즘 사용
            Image resizeImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // 새 이미지 저장하기
            //BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics g = newImage.getGraphics();
            g.drawImage(resizeImage, 0, 0, null);
            g.dispose();
            ImageIO.write(newImage, imgFormat, new File(resizeFullPath.toString()));
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
