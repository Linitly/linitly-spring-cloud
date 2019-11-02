//package com.linitly.service.provider.aop;//package com.siwei.life.insurance.aop;
//
//import com.linitly.service.provider.dao.LogMapper;
//import com.linitly.service.provider.entity.common.BaseEntity;
//import com.linitly.service.provider.entity.common.BaseLog;
//import com.linitly.service.provider.util.JsonUtils;
//import com.linitly.service.provider.util.log.ClassUtil;
//import com.linitly.service.provider.util.log.RequestUtil;
//import io.swagger.annotations.ApiModel;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Aspect
//@Component
//public class LogAspect {
//
//    @Autowired
//    private LogMapper logMapper;
//
//    private static final String SAVE_CODE = "新建";
//    private static final String UPDATE_CODE = "修改";
//    private static final String ENABLE_CODE = "启用";
//    private static final String DISABLE_CODE = "禁用";
//    private static final String DELETE_CODE = "删除";
//
//    @Pointcut("execution(public * com.siwei.life.insurance.dao.*.insert*(..))")
//    public void savePoint() {
//    }
//
//    @Pointcut("execution(public * com.siwei.life.insurance.dao.*.update*(..))")
//    public void updatePoint() {
//    }
//
//    @Pointcut("execution(public * com.siwei.life.insurance.dao.*.enabled*(..))")
//    public void enabledPoint() {
//    }
//
//    @Pointcut("execution(public * com.siwei.life.insurance.dao.*.disabled*(..))")
//    public void disabledPoint() {
//    }
//
//    @Pointcut("execution(public * com.siwei.life.insurance.dao.*.delete*(..))")
//    public void deletePoint() {
//    }
//
//    @AfterReturning("savePoint()")
//    public void saveAround(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        HttpServletRequest request = getRequest();
//        Arrays.stream(args).filter(e -> e instanceof BaseEntity || e instanceof ArrayList).forEach(e -> createLog(e, request, SAVE_CODE));
//    }
//
//    @AfterReturning("updatePoint()")
//    public void updateAround(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        HttpServletRequest request = getRequest();
//        Arrays.stream(args).filter(e -> e instanceof BaseEntity || e instanceof ArrayList).forEach(e -> createLog(e, request, UPDATE_CODE));
//    }
//
//    @AfterReturning("enabledPoint()")
//    public void enabledAround(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        HttpServletRequest request = getRequest();
//        Arrays.stream(args).filter(e -> e instanceof BaseEntity)
//                .forEach(e -> createSpecialLog(e, request, ENABLE_CODE));
//    }
//
//    @AfterReturning("disabledPoint()")
//    public void disabledAround(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        HttpServletRequest request = getRequest();
//        Arrays.stream(args).filter(e -> e instanceof BaseEntity)
//                .forEach(e -> createSpecialLog(e, request, DISABLE_CODE));
//    }
//
//    @AfterReturning("deletePoint()")
//    public void deleteAround(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        HttpServletRequest request = getRequest();
//        Arrays.stream(args).filter(e -> e instanceof BaseEntity)
//                .forEach(e -> createSpecialLog(e, request, DELETE_CODE));
//    }
//
//    private <T> void createLog(T param, HttpServletRequest request, String operation) {
//        if (param instanceof ArrayList) {
//            List<BaseLog> baseLogs = new ArrayList<>();
//            ((ArrayList) param).forEach(e -> {
//                if (e instanceof BaseEntity) {
//                    BaseLog baseLog = generateBaseLog(e, request, operation);
//                    baseLogs.add(baseLog);
//                }
//            });
//            // 保存日志
//            if (!baseLogs.isEmpty()) {
//                String baseLogString = JsonUtils.objectToJson(baseLogs, false);
//
//                logMapper.insertAll(baseLogString);
//            }
//        } else {
//            BaseLog baseLog = generateBaseLog(param, request, operation);
//            // 保存日志
//            if (baseLog != null) logMapper.insert(baseLog);
//        }
//    }
//
//    private <T> void createSpecialLog(T entity, HttpServletRequest request, String operation) {
//        BaseLog logEntity = generateLog(entity, request, operation, 2);
//        // 保存日志
//        if (logEntity != null) logMapper.insert(logEntity);
//    }
//
//    private <T> BaseLog generateBaseLog(T entity, HttpServletRequest request, String operation) {
//        return generateLog(entity, request, operation, 1);
//    }
//
//    /**
//     * 生成日志信息
//     */
//    private <T> BaseLog generateLog(T entity, HttpServletRequest request, String operation, Integer type) {
//        ApiModel apiModel = entity.getClass().getAnnotation(ApiModel.class);
//        if (apiModel != null) {
//            try {
//                String entityProperty = apiModel.value();
//                Field[] fields = ClassUtil.getAllFields(entity);
//
//                // 根据type不同来生成不同log信息
//                String log = null;
//                if (type == 1) {
//                    log = ClassUtil.getEntityLog(entity, fields, operation, entityProperty);
//                } else if (type == 2) {
//                    log = operation + entityProperty;
//                }
//                BaseLog logEntity = new BaseLog();
//                logEntity.setOperation(operation);
//                logEntity.setChangeJson(ClassUtil.generatorLogJson(entity));
//                logEntity.setEntityId(operation.equals(SAVE_CODE) ? null : ClassUtil.getEntityId(entity, fields));
//                logEntity.setLog(log);
//                logEntity.setTableName(ClassUtil.getLogTableName(entity));
//                logEntity.setIp(RequestUtil.getIpAddress(request));
//                // 获取用户类型
//                Integer userType = JwtSystemUtil.getUserType(RequestUtil.getToken(request));
//                logEntity.setOperatorType(userType);
//                logEntity.setOperatorId(getOperatorIdOrOpenId(userType, request));
//
//                return logEntity;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 通用获取request对象工具类
//     */
//    private HttpServletRequest getRequest() {
//        HttpServletRequest request;
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        if (requestAttributes != null) {
//            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
//            request = servletRequestAttributes.getRequest();
//        } else {
//            request = null;
//        }
//        return request;
//    }
//
//    private String getOperatorIdOrOpenId(Integer userType, HttpServletRequest request) {
//        if (userType == null) return null;
//        LogOperatorTypeEnum logOperatorTypeEnum = LogOperatorTypeEnum.getEnum(userType);
//        String operatorIdOrOpenId = null;
//        switch (logOperatorTypeEnum) {
//            case ADMIN_USER:
//                operatorIdOrOpenId = JwtSystemUtil.getAdminUserId(request);
//                break;
//            case PROXY_USER:
//                operatorIdOrOpenId = JwtSystemUtil.getProxyOpenId(request);
//                break;
//            case VISITOR:
//                break;
//            default:
//                break;
//        }
//        return operatorIdOrOpenId;
//    }
//}
