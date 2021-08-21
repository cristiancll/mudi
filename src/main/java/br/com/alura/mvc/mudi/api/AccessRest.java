package br.com.alura.mvc.mudi.api;

import br.com.alura.mvc.mudi.interceptor.AccessInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("access")
public class AccessRest {
    
    @GetMapping
    public List<AccessInterceptor.Access> getAccess(){
        return AccessInterceptor.accessList;
    }
}
