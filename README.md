## 簡易商品收據產生器

系統啟動會載入預設資料，會顯示首頁畫免方便使用者觀看 <br>
可按照輸入商品金額、名稱、位置、數量 進行稅金換算 產生收據 並顯示結果前端頁面 <br>
特定商品以及種類被定義為免稅金 <br>
針對Controller層、service層、dao層、entity都有進行特定單元測試確保流程正常

<hr>

#### 後端使用技術: springboot 、spring-data-jdbc <br>
#### 前端技術: thymeleaf boostrap jquery
#### 單元測試: mockito junit5
#### 資料庫: mysql8.0

<hr>

### 畫面預覽:

#### 初始畫面:
![商品換算器](https://github.com/yaiiow159/shopping-receipt/assets/39752246/a9e24b73-f8c4-44e0-9357-311bb2691c2f)

#### 單一商品換算
![單一商品換算](https://github.com/yaiiow159/shopping-receipt/assets/39752246/411f7c5f-7308-4858-ada4-68bc5ab531a0)

#### 總商品換算
![總商品換算](https://github.com/yaiiow159/shopping-receipt/assets/39752246/0718c7bf-db3e-4494-b282-2bae7232942f)
