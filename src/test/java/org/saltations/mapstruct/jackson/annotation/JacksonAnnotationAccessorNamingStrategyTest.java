package org.saltations.mapstruct.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.ap.internal.util.accessor.ExecutableElementAccessor;
import org.mapstruct.ap.spi.MapStructProcessingEnvironment;
import org.mockito.Mockito;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.beans.Introspector;
import java.lang.annotation.Annotation;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JacksonAnnotationAccessorNamingStrategyTest
{
    @Test
    @DisplayName("Test 1")
    public void canCatchAGetter() throws NoSuchMethodException {

        var strategy = new JacksonAnnotationAccessorNamingStrategy();
        strategy.init(new MapStructProcessingEnvironment() {
            @Override
            public Elements getElementUtils() {
                return null;
            }

            @Override
            public Types getTypeUtils() {
                return null;
            }
        });

        var annotaton = mock(JsonGetter.class);
        var elt = Mockito.mock(ExecutableElement.class);
        when(elt.getAnnotation(JsonGetter.class)).thenReturn(annotaton);
        var result = strategy.isGetterMethod(elt);

        assertTrue(result);

        when(elt.getAnnotation(JsonGetter.class)).thenReturn(null);
        when(elt.getAnnotation(JsonSetter.class)).thenReturn(null);
    }

    @Test
    @DisplayName("Test 2")
    public void canCatchASetter() throws NoSuchMethodException {

        var strategy = new JacksonAnnotationAccessorNamingStrategy();
        strategy.init(new MapStructProcessingEnvironment() {
            @Override
            public Elements getElementUtils() {
                return null;
            }

            @Override
            public Types getTypeUtils() {
                return null;
            }
        });

        var annotaton = mock(JsonSetter.class);
        var elt = Mockito.mock(ExecutableElement.class);
        when(elt.getAnnotation(JsonSetter.class)).thenReturn(annotaton);
        when(elt.getSimpleName()).thenReturn(new Name() {
            @Override
            public boolean contentEquals(CharSequence cs) {
                return "schmoo".equals(cs);
            }

            @Override
            public int length() {
                return "schmoo".length();
            }

            @Override
            public char charAt(int index) {
                return "schmoo".charAt(index);
            }

            @Override
            public CharSequence subSequence(int start, int end) {
                return "schmoo".subSequence(start,end);
            }
        });

        when(elt.getReturnType()).thenReturn(new TypeMirror() {
            @Override
            public TypeKind getKind() {
                return null;
            }

            @Override
            public List<? extends AnnotationMirror> getAnnotationMirrors() {
                return null;
            }

            @Override
            public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
                return null;
            }

            @Override
            public <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationType) {
                return null;
            }

            @Override
            public <R, P> R accept(TypeVisitor<R, P> v, P p) {
                return null;
            }
        });

        assertFalse(strategy.isGetterMethod(elt));
        assertFalse(strategy.isSetterMethod(elt));
    }
}
