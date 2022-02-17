package com.example.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2022-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//@ApiModel(value="ArticleContent对象", description="")
public class ArticleContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    private String content;


}
