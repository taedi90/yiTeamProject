package kr.or.yi.teamProject.product.mapper;

import kr.or.yi.teamProject.product.dto.ProductInfoAnnounce;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ProductInfoAnnounceMapper {
    int insertProductInfoAnnounce(ProductInfoAnnounce productInfoAnnounce);
    int deleteProductInfoAnnounce(ProductInfoAnnounce productInfoAnnounce);
    int updateProductInfoAnnounce(ProductInfoAnnounce productInfoAnnounce);
    ArrayList<ProductInfoAnnounce> selectProductInfoAnnounceList();
    ProductInfoAnnounce selectProductInfoAnnounce(ProductInfoAnnounce productInfoAnnounce);
}
