package com.endava.groceryshopservice.entities.dto;

import com.endava.groceryshopservice.entities.Product;
import com.endava.groceryshopservice.entities.types.SizeTypes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Api(value = "Most sold product model information")
@Data
public class MostSoldProductDTO {

    @ApiModelProperty(value = "Url to an image",
            example = "https://www.mantralabsglobal.com/404")
    private String image;

    @ApiModelProperty(value = "Product's name", example = "Eggs")
    private String name;

    @ApiModelProperty(value = "Product's quantity", example = "1")
    private Integer quantity;

    public MostSoldProductDTO(Product product) {
        image = product.getImage();
        name = product.getName();
    }
}