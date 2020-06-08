package demo;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(TestFilter.ORDER)
public class TestFilter extends OncePerRequestFilter {

    public static final int ORDER = Ordered.LOWEST_PRECEDENCE - 10;  // - 10 to allow applications/ libraries to register filters after this one

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Assert.notNull(request.getUserPrincipal(), "userPrincipal");

        filterChain.doFilter(request, response);
    }
}