package org.example.module10homework2;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.module10homework2.entity.Attachment;
import org.example.module10homework2.entity.AttachmentContent;
import org.example.module10homework2.entity.Post;
import org.example.module10homework2.repo.AttachmentContentRepository;
import org.example.module10homework2.repo.AttachmentRepository;
import org.example.module10homework2.repo.PostRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@EnableCaching
@SpringBootApplication
public class Module10Homework2Application {

    public static void main(String[] args) {
        SpringApplication.run(Module10Homework2Application.class, args);
    }

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setCacheNames(List.of("post"));
        return cacheManager;
    }

    @SneakyThrows
    @Bean
    public ApplicationRunner applicationRunner(ObjectMapper objectMapper,
                                               PostRepository postRepository, AttachmentContentRepository attachmentContentRepository, AttachmentRepository attachmentRepository) {
        return args -> {
            Post[] posts = objectMapper.readValue(new URL("https://jsonplaceholder.typicode.com/posts"), Post[].class);

            String imageUrl = "https://images.unsplash.com/photo-1516117172878-fd2c41f4a759";
            byte[] imageBytes = readImageFromUrl(imageUrl);

            Attachment attachment = new Attachment(1);
            AttachmentContent attachmentContent = new AttachmentContent(1, attachment, imageBytes);

            attachmentRepository.save(attachment);
            attachmentContentRepository.save(attachmentContent);

            for (Post post : posts) {
                post.setPhoto(attachment);
            }
            postRepository.saveAll(Arrays.asList(posts));
        };
    }


    private byte[] readImageFromUrl(String url) throws IOException {
        BufferedImage image = ImageIO.read(new URL(url));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }
}
