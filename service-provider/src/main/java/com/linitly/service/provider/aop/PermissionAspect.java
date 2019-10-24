package com.linitly.service.provider.aop;

import com.linitly.service.provider.annotation.RequirePermission;
import com.linitly.service.provider.annotation.RequireRole;
import com.linitly.service.provider.enums.GlobalEnum;
import com.linitly.service.provider.exception.CommonException;
import com.linitly.service.provider.util.RedisOperator;
import com.linitly.service.provider.util.permission.PermissionAnnotationUtil;
import com.linitly.service.provider.util.permission.RoleAndPermissionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Set;

@Aspect
@Component
public class PermissionAspect {

	@Autowired
	private RedisOperator redisOperator;

	@Pointcut("execution(public * com.linitly..controller..*.*(..))")
	public void permissionAspect() {
	}

	@Around("permissionAspect()")
	public Object permissionAspectAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("进入aop");
//		try {
//			Object target = joinPoint.getTarget();
//			MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//			boolean hasAnnotation = PermissionAnnotationUtil.hasAnnotation(target.getClass(), methodSignature.getName(),
//					methodSignature.getParameterTypes());
//			if (hasAnnotation) {
//
//				RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//				ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
//				HttpServletRequest request = servletRequestAttributes.getRequest();
//
//				String token = RequestUtil.getToken(request);
//				Set<String> roles = redisOperator.setMembers(JWTConstant.ADMIN_ROLES_PREFIX + token);
//                Set<String> permissions = redisOperator.setMembers(JWTConstant.ADMIN_PERMISSIONS_PREFIX + token);
//
//				String[] requireRolesOrPermissions = PermissionAnnotationUtil.parseRoleOrPermission(target.getClass(),
//						methodSignature.getName(), methodSignature.getParameterTypes(), RequireRole.class);
//				if (requireRolesOrPermissions != null && requireRolesOrPermissions.length > 0) {
//					boolean hasRole = RoleAndPermissionUtil.hasRoleOrPermission(new ArrayList<>(roles), requireRolesOrPermissions,
//							target.getClass(), methodSignature.getName(), methodSignature.getParameterTypes(),
//							RequireRole.class);
//					if (!hasRole)
//						throw new CommonException(GlobalEnum.NO_PERMISSION);
//				}
//
//				requireRolesOrPermissions = PermissionAnnotationUtil.parseRoleOrPermission(target.getClass(),
//						methodSignature.getName(), methodSignature.getParameterTypes(), RequirePermission.class);
//				if (requireRolesOrPermissions != null && requireRolesOrPermissions.length > 0) {
//					boolean hasPermission = RoleAndPermissionUtil.hasRoleOrPermission(new ArrayList<>(permissions),
//							requireRolesOrPermissions, target.getClass(), methodSignature.getName(),
//							methodSignature.getParameterTypes(), RequirePermission.class);
//					if (!hasPermission)
//						throw new CommonException(GlobalEnum.NO_PERMISSION);
//				}
//			}
//		} catch (NoSuchMethodException | SecurityException e) {
//			e.printStackTrace();
//			throw new CommonException(GlobalEnum.CLASS_METHOD_ERROR);
//		}
		Object object = joinPoint.proceed();
		return object;
	}
}
