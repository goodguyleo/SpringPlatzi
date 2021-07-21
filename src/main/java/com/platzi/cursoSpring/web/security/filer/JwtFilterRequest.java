package com.platzi.cursoSpring.web.security.filer;

// Esta clase se encarga de atrapar todas las peticiones que recibe la aplicacion
// Esta clase verifica si el JWT es correcto

import com.platzi.cursoSpring.domain.service.PlatziUserDetailsService;
import com.platzi.cursoSpring.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
// OncePerRequestFilter hace que esta filtro se ejecute cada que se realice una peticion
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //      aqui verificamos si lo que viene en el encabezado de la peticion es correcto y si el token es correcto
        String authorizationHeader = request.getHeader("Authorization");

        //      si el header de la autorizacion NO es nulo y comienza con el prefijo Bearer, es porque dentro de el
        //      viene un JWT y debe ser capturado desde la posicion del string 7 para no capturar la palabra Bearer
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            String jwt = authorizationHeader.substring(7);
            String username = jwtUtil.extractUsername(jwt);

            //      Comprobamos que aun no exista ninguna autenticacion para este usuario
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = platziUserDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
