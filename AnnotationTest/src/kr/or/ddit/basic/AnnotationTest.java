package kr.or.ddit.basic;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) throws Exception {
		
		
		// 선언된 메서드 목록을 조회하고, 메서드를 실행해 보기
		Class<?> klass = Service.class;
		// Service service = new Service();
		Service service = (Service) klass.newInstance();
		
		Method[] methodArr =  klass.getDeclaredMethods();
		/* = Method[] methodArr = Service.class.getDeclaredMethods(); */
		
		for(Method m : methodArr) {
			System.out.print(m.getName() + "=>");
			Annotation[] annos = 
					m.getDeclaredAnnotations();
			
			for(Annotation anno : annos) {
				
				if(anno.annotationType().getSimpleName().equals("PrintAnnotation")) {
					PrintAnnotation printAnn = (PrintAnnotation) anno;
					for(int i=0; i<printAnn.count(); i++) {
						System.out.print(printAnn.value());
					}
				}
			}
			m.invoke(service); //메서드 실행
			System.out.println();
		}
	}
}
