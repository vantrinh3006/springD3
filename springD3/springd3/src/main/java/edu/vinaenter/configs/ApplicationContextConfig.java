package edu.vinaenter.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScans({ @ComponentScan(value = "edu.vinaenter.*") }) // quét qua file này mới đến đc controller mới chạy
public class ApplicationContextConfig {

	@Bean(name = "viewResolver") // is an object, khởi tạo confic nào,
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver views = new InternalResourceViewResolver();
		views.setPrefix("/WEB-INF/views/"); // prefix: where is runfolder
		views.setSuffix(".jsp");
		return views;
	}

	@Bean(name = "multipartResolver")	// để uploadfile
	public MultipartResolver getMultipartResolver() {
		CommonsMultipartResolver resover = new CommonsMultipartResolver();
		// 1MB
		// resover.setMaxUploadSize(1 * 1024 * 1024);

		return resover;
	}
}
