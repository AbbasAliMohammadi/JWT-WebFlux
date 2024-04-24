package af.gov.reactiveAuthService.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/")
public class TestResource {
    @GetMapping("test")
	public Mono<String> test(){
		return Mono.just("test");
	}
}
