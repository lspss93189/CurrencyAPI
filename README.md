# 國泰世華JAVA engineer線上作業
## 專案需求
1. Build Tool:Maven
2. JDK:8
3. Sprint-voot
4. 資料庫：H2(ORM使用Spring Data JPA)
5. 資料建立SQL語法：/src/main/resources/data.sql
## 功能簡述
1. 呼叫coindesk API顯示原始內容
2. 呼叫coindesk API顯示資料轉換後內容（含更新時間、幣別、幣別中文名稱、匯率）
3. 幣別資料表CRUD功能
## API URL
1. 呼叫coindesk API顯示原始內容：http://localhost:8080/coindesk/raw
2. 呼叫coindesk API顯示資料轉換後內容：http://localhost:8080/coindesk/transformed
3. GET：http://localhost:8080/currency
4. POST：http://localhost:8080/currency
5. PUT：http://localhost:8080/currency/{id}
6. DELETE：http://localhost:8080/currency/{id}
