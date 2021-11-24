package kr.or.yi.teamProject.product.dto;

import kr.or.yi.teamProject.common.dto.Pager;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ItemPager extends Pager {

    private String publish;
    private String hide;

    public ItemPager(){
        super();
    }

    @Override
    public String toString() {
        return super.toString() +
                "ItemPager{" +
                "publish='" + publish + '\'' +
                ", hide='" + hide + '\'' +
                '}';
    }
}