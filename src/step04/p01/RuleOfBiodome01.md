# 🐾 SOLID 원칙

<br/>

## 목차

- [SOLID 원칙이란](#solid-원칙이란-)

- [다섯 가지 원칙 소개](#다섯-가지-원칙-소개)

- [도서관 시스템 분석](#solid-원칙을-바탕으로-분석해-보자)

<br/>

## SOLID 원칙이란 ❔❓

![Image](https://github.com/user-attachments/assets/09099562-fcbf-4c32-af37-e69e82e01f15)
**SOLID 원칙**은 로버트 C. 마틴이 2000년대 초반에 명명한 객체 지향 프로그래밍의 **다섯 가지 기본 원칙**으로, 유지보수성, 확장성, 테스트 용이성을 높이고 유연하고 견고한 소프트웨어를 만들기 위해
필요하다.

---
<br/>

## 다섯 가지 원칙 소개

### SRP (Single Responsibility Principle, 단일 책임 원칙)

각 클래스는 **하나의 책임**만을 가져야 하며, 변경되는 이유가 한가지여야 한다.
단일 책임 원칙을 제대로 지키면 변경이 필요할 때 수정할 대상이 명확해진다.


---

### OCP (Open/Closed Principle, 개방/폐쇄 원칙)

**확장**에 대해서는 **개방**되어야 하지만, **수정**에 대해서는 **폐쇄**되어야 한다. 기존 코드를 변경하지 않고 새로운 기능을 추가할 수 있도록, 확장을 위한 인터페이스를 제공하고 확장 구현을 수용해야
한다. 본질적으로 **추상화**를 얘기한다.

---

### LSP (Liskov Substitution Principle, 리스코프 치환 원칙)

**하위 타입**은 **상위 타입**을 **대체**할 수 있어야 하며, 상위 타입을 사용하는 곳에 하위 타입을 넣어도 프로그램이 정상적으로 작동해야 한다. 즉, 하위 타입이 상위 타입의 **모든 특성**을 만족해야
한다.

---

### ISP (Interface Segregation Principle, 인터페이스 분리 원칙)

클라이언트가 사용하지 않는 메서드는 인터페이스에서 제거해야 한다. 즉, **클라이언트의 목적과 용도에 적합한 인터페이스 만**을 제공하는 것이다. 인터페이스 분리 원칙을 준수함으로써 기존 클라이언트에 영향을 주지
않은 채로 유연하게 객체의 기능을 확장하거나 수정할 수 있다.

---

### DIP (Dependency Inversion Principle, 의존 역전 원칙)

상위 모듈은 하위 모듈에 의존해서는 안 되며, 둘 다 **추상화에 의존**해야 한다. 즉, 구현이 아닌 **인터페이스에 의존**해야 한다. 상위 모듈은 추상적인 인터페이스를 통해 하위 모듈과 상호 작용해야 한다.

##### [상위 모듈] : 입력과 출력으로부터 먼(비즈니스와 관련된) 추상화된 모듈

##### [하위 모듈] : 입력과 출력으로부터 가까운(HTTP, 데이터베이스, 캐시 등과 관련된) 구현 모듈

---

<br/>

## SOLID 원칙을 바탕으로 분석해 보자❗

- 제공된 코드 : `RuleOfBiodome01_before.java`

<br/>

### <u>원칙에 어긋나는 부분</u>

### ✅ 1. SRP (단일 책임 원칙)

#### 코드:

```java
// User class
abstract class User {
    public void borrowBook(Book book) {
        if (!book.isBorrowed) {
            book.isBorrowed = true;
        }
    }

    public void returnBook(Book book) {
        if (book.isBorrowed) {
            book.isBorrowed = false;
        }
    }

    abstract void addBook(Book book, Library library);

    abstract void removeBook(Book book, Library library);
}
```

#### 이유:

하나의 클래스가 책 빌리기, 책 반납, 책 추가, 책 삭제까지 많은 책임을 갖고 있다.

#### 해결책:

하나의 책임 외의 것들은 별도로 분리하기.

---

### ✅ 2. OCP (개방/폐쇄 원칙)

#### 코드:

```java
// Library class
public void addMember(Member member) {
    users.add(member);
}

public void addManager(Manager manager) {
    users.add(manager);
}
```

#### 이유:

Member, Manager 각각 메서드를 만들어서 확장을 할 때마다 또 add***로 코드 수정을 해야 한다.

#### 해결책:

User를 사용해 확장이 용이하도록 할 것 (ex: addUser(User user))

---

### ✅ 3. LSP (리스코프 치환 원칙)

#### 코드:

```java
// Member class
public void addBook(Book book, Library library) {
    System.out.println("Member can't add book");
}

public void removeBook(Book book, Library library) {
    System.out.println("Member can't remove book");
}

// 만약 상위 타입으로 사용하면?
User member = new Member("U001", "Kim");
member.

addBook(book, library);

```

#### 이유:

addBook이 정상적으로 작동하지 않는다. 하위 타입이 상위 타입의 모든 특성을 만족하지 않는다.

#### 해결책:

공통 기능만 User에 두고, Manager의 기능(addBook, removeBook)은 인터페이스나 별도 클래스에 분리하기.

---





