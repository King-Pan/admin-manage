package club.javalearn.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.config.EnablePluginRegistries;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.OperationParameterReader;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created with IntelliJ IDEA.
 *
 * @author king-pan
 * Date: 2018/9/11
 * Time: 下午3:15
 * Description: No Description
 */
@Configuration
@EnableSwagger2
@EnablePluginRegistries(
        {
                OperationParameterReader.class
        }
)
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("club.javalearn.admin"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("亚信湖北项目管理工具API文档")
                .description("亚信湖北项目管理工具API文档")
                //服务条款网址
                //.termsOfServiceUrl("http://blog.csdn.net/forezp")
                .version("1.0")
                .contact(new Contact("King-pan", "https://www.github.com/king-pan", "pwpw1218@gmail.com"))
                .build();
    }


}
