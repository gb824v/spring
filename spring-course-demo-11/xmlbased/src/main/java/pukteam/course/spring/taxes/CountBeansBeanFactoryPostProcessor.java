package pukteam.course.spring.taxes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class CountBeansBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private static Logger logger = LogManager.getLogger(CountBeansBeanFactoryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        logger.info("About to start Spring construction process on {} beans...", beanFactory.getBeanDefinitionCount());
        BeanDefinition tikva = beanFactory.getBeanDefinition("tikva");
        logger.info("The bean named tikva is of class [{}]", tikva.getBeanClassName());
        logger.info("===============================");
    }
}
