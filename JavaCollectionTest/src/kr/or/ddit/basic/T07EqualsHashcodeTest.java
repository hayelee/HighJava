package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class T07EqualsHashcodeTest {
	/*
	  해시함수(hash function)는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑해주는 함수이다.
	  해시함수에 의해 얻어지는 값은 해시값, 해시코드, 해시 체크썸 또는 간단하게 해시라고 한다.
	  
	  HashSet, HashMap, Hashtable과 같은 객체들을 사용할 경우 객체가 서로 같은지를 비교하기 위해
	  equals()와 hashCode()를 호출한다. 그래서 객체가 서로 같은지 여부를 결정하려면 두 메서드를 재정의해야 한다.
	  HashSet, HashMap, Hashtable에서 객체가 같은지 여부는 데이터를 추가할 때 검사한다.
	  
	   - equals() => 두 객체의 내용(값)이 같은지 비교하는 메서드.
	   - hashCode() => 객체에 대한 해시코드값을 반환하는 메서드.
	   
	   - equals()와 hashCode()에 관한 규칙
	   1. 두 객체가 같으면 반드시 같은 hashcode를 가져야 한다.
	   2. 두 객체가 같으면 equals()메서드를 호출했을때 true값을 반환해야 한다.
	      즉, 객체 a, b가 같다면 a.equals(b)와 b.equals(a) 둘 다 true여야 한다.
	   3. 두 객체의 hashcode가 같다고 해서 두 객체가 반드시 같은 객체는 아니다.
	      하지만 두 객체가 같으면 반드시 hashcode가 같아야 한다.
	   4. equals()를 override하면 반드시 hashCode()도 override 해야 한다. //같은 객체라고 판단할 수 있게
	   5. hashCode()는 기본적으로 Heap 메모리에 있는 각 객체에 대한 메모리 주소 매핑 정보를   
	      기반으로 한 정수값을 반환한다. 그러므로 클래스에서 hashCode()메서드를 override하지
	      않으면 절대로 두 객체가 같은 해시값을 반환하지 않는다.
	      
	   - hashCode()에서 사용하는 '해싱알고리즘'에서 서로 다른 객체에 대하여 같은 hashCode값을 만들어낼 수 있다.
	     그래서 객체가 같지 않더라도 hashcode값이 같을 수 있다.
	 */
	public static void main(String[] args) {
		
		System.out.println("Aa".hashCode());    //문자는 다른데 해시코드값은 같아
		System.out.println("BB".hashCode());    //멘붕올 수 있음 ㅎ 이럴때는 equals로 비교해줘야해 
		
		Person p1 = new Person(1, "홍길동");
		Person p2 = new Person(1, "홍길동");
		Person p3 = new Person(1, "이순신");
		
		System.out.println("p1.equals(p2) : " + p1.equals(p2));
		System.out.println("p1 == p2 : " + (p1 == p2));
		
		Set<Person> pSet = new HashSet<Person>(); 
		
//		pSet.add(p1);
//		pSet.add(p2);
		
		System.out.println("pSet.add(p1) 성공 여부 : " + pSet.add(p1));
		System.out.println("pSet.add(p2) 성공 여부 : " + pSet.add(p2));
		
		
		System.out.println("p1, p2 등록 후 데이터 : ");
		Iterator<Person> it = pSet.iterator(); //iterator 향상된 for문 사용한 것
		while(it.hasNext()) {
			Person p = it.next();
			System.out.println(p.getId() + " : " + p.getName());
		}
		
		System.out.println("add(p3) 성공 여부 : " + pSet.add(p3));
		
		System.out.println("add(p3) 후 데이터 : ");
		for(Person p : pSet) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		
		System.out.println("remove(p2) 성공 여부 : " + pSet.remove(p2));
		System.out.println("add(p2) 후 데이터 : ");
		for(Person p : pSet) {
			System.out.println(p.getId() + " : " + p.getName());
		}
	}
}

class Person {
	private int id;
	private String name;
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return id == other.id && Objects.equals(name, other.name);
	}
	
//	@Override //그대로 내버려두면 equals가 다르다고 판단하기 때문에 override를 시켜서 같다고 만들어줘야함
//	public String toString() {
//		return "Person [id=" + id + ", name=" + name + "]";
//	}
//	
//	public boolean equals(Object obj) {
//		
//		Person p = (Person) obj;
//		return (this.getId() == p.getId()) && this.getName().equals(p.getName());
//	}
//	 @Override // 같은 해시코드니까 중복이라고 판단해서 제거
//	public int hashCode() {
//		 
//		 return(name+id).hashCode();
//	 }
}
