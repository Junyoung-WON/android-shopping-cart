# android-shopping-cart


### step1

- 첫 번째 화면에서 스크롤 형태로 제품들을 보여준다.
  - 이때, RecyclerView에 GridLayoutManager를 설정하여 구현한다.
  - 화면에는 사진과 제품의 이름, 가격이 보인다.
  - 이미지 로딩을 위해 Glide를 사용한다.
  - 액션 바의 장바구니 아이콘을 클릭하면, 장바구니 페이지로 이동한다.
  - 액션 바의 제목을 보여준다.

- 상품 상세 페이지
  - 상품의 이미지를 보여준다.
  - 상품의 이름과 가격을 보여준다.
  - 장바구니 담기 버튼을 누르면, 해당 상품이 장바구니에 추가된다.
    - 장바구니 담기에 성공하면 스낵바를 띄워서 알린다.
      - 장바구니 페이지로 이동할지 물어보는 버튼도 함께 나타난다.
  - 액션 바의 오른쪽 위에 닫기 버튼이 있다.
  
- 장바구니 페이지
  - 뒤로가기 버튼을 눌러서 이전 페이지로 이동한다.
  - 장바구니에 담았던 상품 목록을 보여준다.
  - 각 아이템에는 상품의 사진과 이름, 가격이 있다.
  - 삭제 버튼을 누르면 해당 상품이 장바구니에서 삭제된다.
