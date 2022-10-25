package site.metacoding.white.utill;

import org.junit.jupiter.api.Test;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class Product {
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
    private String mcp;// 제조사
}

@Setter
@Getter
class ProductDto {
    private String name;
    private Integer price;
    private Integer qty;
}

public class MapperTest {

    @Test
    public void 매핑하기1() {
        // 1.Product 객체생성(디폴트)
        Product product = new Product();

        // 2.값넣기
        product.setId(null);
        product.setName(null);
        // 3.ProductDto 객체생성(디폴트)
        ProductDto productDto = new ProductDto();
        // 4.Product->ProductDto로 옮기기
        productDto.setName(product.getName());
        // 5.ProductDto-> product 변경
    }
}
