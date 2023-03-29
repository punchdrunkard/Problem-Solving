# 실수 노트

## 목차

  <ul>
    <li>
      <a href="#max_element-사용시-주의점">`max_element` 사용시 주의점</a>
     </li>
     <li>
      <a href="#소수점-자릿수-설정하여-출력하기">소수점 자릿수 설정하여 출력하기</a>
     </li>
    
  </ul>

## max_element 사용시 주의점

- `max_element` 를 사용할 때, 전달하는 인자가 비어있는 경우, **런타임 에러**가 발생할 수 있다. 따라서, `max_element` 를 호출하기 전에 해당 배열이 비어있는 지 확인하는 과정이 필요하다.

## 소수점 자릿수 설정하여 출력하기

`iomanip` 라이브러리의 `setprecision` 함수를 사용한다.
또한 출력할 때, 고정 소수점 표현을 설정하기 위하여 `fixed`를 사용한다.

```cpp
#include <iostream>
#include <iomnaip> // setprecision

using namespace std;

int main(){
    double pi = 3.1415926535;

    cout << fixed << setprecision(3) << pi << endl; // 3.141
    cout << fixed << setprecision(2) << pi << endl; // 3.14
    cout << fixed << setprecision(1) << pi << endl; // 3.1

    return 0;
}

```
