package com.git.hui.boot.beanutil.copier;

import com.git.hui.boot.beanutil.copier.cglib.PureCglibBeanCopier;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

/**
 * @author wuzebang
 * @date 2021/4/8
 */
@Component
public class PureCglibCopier {
    /**
     * cglib 对象转换
     *
     * @param source
     * @param target
     * @param <K>
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <K, T> T copy(K source, Class<T> target) throws IllegalAccessException, InstantiationException {
        BeanCopier copier = BeanCopier.create(source.getClass(), target, false);
        T res = target.newInstance();
        copier.copy(source, res, null);
        return res;
    }

    public <K, T> T copyAndParse(K source, Class<T> target) throws IllegalAccessException, InstantiationException {
        // todo copier 可以缓存起来，避免每次重新创建
        BeanCopier copier = PureCglibBeanCopier.create(source.getClass(), target, false);
        T res = target.newInstance();
        copier.copy(source, res, null);
        return res;
    }
}
