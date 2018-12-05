package com.haiyu.manager.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.haiyu.manager.filter.KickoutSessionFilter;
import com.haiyu.manager.shiro.MyRealm;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Title: ShiroConfig
 * @Description: shiro配置
 * @author: youqing
 * @version: 1.0
 * @date: 2018/9/11 10:33
 */
@Configuration
public class ShiroConfig {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件过滤器
     * </br>1,配置shiro安全管理器接口securityManage;
     * </br>2,shiro 连接约束配置filterChainDefinitions;
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        logger.info("Shiro拦截器工厂类注入开始");

        // 配置shiro安全管理器 SecurityManager
        bean.setSecurityManager(securityManager);
        //添加kickout认证
        HashMap<String,Filter> hashMap=new HashMap<String,Filter>();
        hashMap.put("kickout",kickoutSessionFilter());
        bean.setFilters(hashMap);

        // 指定要求登录时的链接
        bean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        bean.setSuccessUrl("/home");
        // 未授权时跳转的界面;
        bean.setUnauthorizedUrl("/error");

        // filterChainDefinitions拦截器map必须用：LinkedHashMap，因为它必须保证有序
        Map<String, String> filterMap = new LinkedHashMap<>();
        // 放行登录页面
        filterMap.put("/login", "anon");
        // 配置退出过滤器,具体的退出代码Shiro已经实现
        filterMap.put("/logout", "logout");
        //配置记住我或认证通过可以访问的地址
        filterMap.put("/user/userList", "user");
        filterMap.put("/", "user");

        // 配置不会被拦截的链接 从上向下顺序判断
        filterMap.put("/css/*", "anon");
        filterMap.put("/js/*", "anon");
        filterMap.put("/js/*/*", "anon");
        filterMap.put("/js/*/*/*", "anon");
        filterMap.put("/images/*/**", "anon");
        filterMap.put("/layui/*", "anon");
        filterMap.put("/layui/*/**", "anon");
        filterMap.put("/treegrid/*", "anon");
        filterMap.put("/treegrid/*/*", "anon");
        filterMap.put("/fragments/*", "anon");
        filterMap.put("/layout", "anon");
        filterMap.put("/home", "anon");
        filterMap.put("/user/login", "anon");

        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问【放行】-->
        filterMap.put("/**", "kickout,authc");
        filterMap.put("/*/*", "authc");
        filterMap.put("/*/*/*", "authc");
        filterMap.put("/*/*/*/**", "authc");

        // 添加 shiro 过滤器
        bean.setFilterChainDefinitionMap(filterMap);
        logger.info("Shiro拦截器工厂类注入成功");

        return bean;
    }

    /**
     * shiro安全管理器设置realm认证和ehcache缓存管理
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();

        // 关联realm
        manager.setRealm(shiroRealm());
        //注入ehcache缓存管理器;
        manager.setCacheManager(ehCacheManager());
        //注入session管理器;
        manager.setSessionManager(sessionManager());
        //注入Cookie记住我管理器
        manager.setRememberMeManager(rememberMeManager());

        return manager;
    }

    /**
     * 3.创建身份认证 Realm
     */
    @Bean
    public MyRealm shiroRealm() {
        MyRealm realm = new MyRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }


    /**
     * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码,更改密码生成规则和校验的逻辑一致即可; ）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于 // md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }


    /**
     * RememberMe 的功能
     */
    // 创建 Cookie
    @Bean
    public SimpleCookie remeberMeCookie() {
        logger.info("记住我，设置cookie过期时间！");
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // //记住我cookie生效时间30天 ,单位秒  [10天]
        cookie.setMaxAge(864000);
        // 设置只读模型
        //cookie.setHttpOnly(true);
        return cookie;
    }

    /**
     * 配置cookie记住我管理器
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        logger.debug("配置cookie记住我管理器！");
        CookieRememberMeManager cookieRememberMeManager=new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(remeberMeCookie());
        return cookieRememberMeManager;
    }


    /**
     *
     * 功能描述: 同一个用户多设备登录限制
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/23 10:22
     */
    public KickoutSessionFilter kickoutSessionFilter(){
        KickoutSessionFilter kickoutSessionFilter = new KickoutSessionFilter();
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        //这里我们还是用之前shiro使用的ehcache实现的cacheManager()缓存管理
        //也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
        kickoutSessionFilter.setCacheManager(ehCacheManager());
        //用于根据会话ID，获取会话进行踢出操作的；
        kickoutSessionFilter.setSessionManager(sessionManager());
        //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
        kickoutSessionFilter.setKickoutAfter(false);
        //同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
        kickoutSessionFilter.setMaxSession(1);
        //被踢出后重定向到的地址；
        kickoutSessionFilter.setKickoutUrl("/login?kickout=1");
        return kickoutSessionFilter;
    }


    /**
     * ehcache缓存管理器；shiro整合ehcache：
     * 通过安全管理器：securityManager
     * 单例的cache防止热部署重启失败
     * @return EhCacheManager
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        logger.debug("shiro整合ehcache缓存：ShiroConfiguration.getEhCacheManager()");
        EhCacheManager ehcache = new EhCacheManager();
        CacheManager cacheManager = CacheManager.getCacheManager("shiro");
        if(cacheManager == null){
            try {
                cacheManager = CacheManager.create(ResourceUtils.getInputStreamForPath("classpath:config/ehcache.xml"));
            } catch (CacheException | IOException e) {
                e.printStackTrace();
            }
        }
        ehcache.setCacheManager(cacheManager);
        return ehcache;
    }

    /**
     *
     * 功能描述: sessionManager添加session缓存操作DAO
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/23 10:31
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

		sessionManager.setSessionDAO(enterCacheSessionDAO());
		sessionManager.setSessionIdCookie(sessionIdCookie());
		return sessionManager;
	}

    /**
     * EnterpriseCacheSessionDAO shiro sessionDao层的实现；
     * 提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
     */
    @Bean
    public EnterpriseCacheSessionDAO enterCacheSessionDAO() {
        EnterpriseCacheSessionDAO enterCacheSessionDAO = new EnterpriseCacheSessionDAO();
        enterCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        return enterCacheSessionDAO;
    }


    /**
     *
     * 功能描述: 自定义cookie中session名称等配置
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/11/23 10:34
     */
    @Bean
    public SimpleCookie sessionIdCookie() {
        //DefaultSecurityManager
        SimpleCookie simpleCookie = new SimpleCookie();
        //sessionManager.setCacheManager(ehCacheManager());
        //如果在Cookie中设置了"HttpOnly"属性，那么通过程序(JS脚本、Applet等)将无法读取到Cookie信息，这样能有效的防止XSS攻击。
        simpleCookie.setHttpOnly(true);
        simpleCookie.setName("SHRIOSESSIONID");
        //单位秒
        simpleCookie.setMaxAge(86400);
        return simpleCookie;
    }



    /**
     * 整合 Shiro 标签
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
