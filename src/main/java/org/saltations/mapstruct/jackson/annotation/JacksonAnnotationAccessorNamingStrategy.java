package org.saltations.mapstruct.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.mapstruct.ap.spi.AccessorNamingStrategy;
import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;

import javax.lang.model.element.ExecutableElement;

/**
 * Represents JacksonAnnotationAccessorNamingStrategy // TODO Document what JacksonAnnotationAccessorNamingStrategy represents
 * <p>
 * Responsible for   // TODO Document JacksonAnnotationAccessorNamingStrategy responsibilities, if any
 * <ol>
 *  <li></li>
 *  <li></li>
 *  <li></li>
 * </ol>
 * <p>
 * Collaborates with // TODO Document JacksonAnnotationAccessorNamingStrategy collaborators, if any
 */

public class JacksonAnnotationAccessorNamingStrategy extends DefaultAccessorNamingStrategy implements AccessorNamingStrategy
{
    @Override
    public boolean isGetterMethod(ExecutableElement method)
    {
        if (method.getAnnotation(JsonGetter.class) != null)
        {
            return true;
        }

        return super.isGetterMethod(method);
    }

    @Override
    public boolean isSetterMethod(ExecutableElement method)
    {
        if (method.getAnnotation(JsonSetter.class) != null)
        {
            return true;
        }

        return super.isSetterMethod(method);
    }
}
