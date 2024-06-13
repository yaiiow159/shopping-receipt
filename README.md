## 簡易商品收據產生器

系統啟動會載入預設資料，會顯示首頁畫免方便使用者觀看 <br>
可按照輸入商品金額、名稱、位置、數量 進行稅金換算 產生收據 並顯示結果前端頁面 <br>
特定商品以及種類被定義為免稅金 <br>
針對Controller層、service層、dao層、entity都有進行特定單元測試確保流程正常 <br>
金額、稅金、稅率等金錢相關換算資料使用BigDecimal提升精確度避免換算時造成金額落差

#### V2 修正問題
修正 entity類別 @NotEmpty註解使用錯誤 改為使用@NotNull判斷非空 <br>
修正 taxRate預設值錯誤問題，使用小數位來表示避免運算錯誤 <br>
修正 roundedUp工具類方法 運算錯誤問題 並添加單元測試確認正確性 <br>
修正 前端頁面下拉式選單 reset問題

<hr>

#### 後端使用技術: springboot 、spring-data-jdbc <br>
#### 前端技術: thymeleaf boostrap jquery
#### 單元測試: mockito junit5
#### 資料庫: mysql8.0

<hr>

### 畫面預覽:

#### 初始畫面:
![商品收據產生器](https://github.com/yaiiow159/shopping-receipt/assets/39752246/b2241da7-773a-44d0-a37c-2b01cc021d2b)

#### 單一商品換算
![單一商品換算](https://github.com/yaiiow159/shopping-receipt/assets/39752246/4aed3563-ff5c-48a2-a0cd-b82cd01721dd)

#### 總商品換算
![商品總換算](https://github.com/yaiiow159/shopping-receipt/assets/39752246/8164571f-acdf-46ce-87f0-1966f6e9fa38)
