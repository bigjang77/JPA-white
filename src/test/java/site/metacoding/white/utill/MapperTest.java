package site.metacoding.white.utill;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@NoArgsConstructor // 스프링에서 DB -> rs -> Entity (전략:디폴트생성자를 호출한뒤 setter)
@AllArgsConstructor
@Setter
@Getter
class Product {
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
    private String mcp;// 제조사

}

@AllArgsConstructor
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
        Product product = new Product(1, "바나나", 1000, 100, "델몬트");
        // // 2.값넣기
        // product.setId(1);
        // product.setName("바나나");
        // product.setPrice(1000);
        // product.setQty(100);
        product.setMcp("델몬트");
        // 3.ProductDto 객체생성(디폴트)
        ProductDto productDto = new ProductDto(product.getName(), product.getPrice(), product.getQty());
        // // 4.Product->ProductDto로 옮기기
        // productDto.setName(product.getName());
        // productDto.setPrice(product.getPrice());
        // productDto.setQty(product.getQty());
        // 5.ProductDto-> product 변경
        Product product2 = new Product(null, productDto.getName(), productDto.getPrice(), productDto.getQty(), null);
        // product2.setName(productDto.getName());
        // product2.setPrice(productDto.getPrice());
        // product2.setQty(productDto.getQty());

    }
}
