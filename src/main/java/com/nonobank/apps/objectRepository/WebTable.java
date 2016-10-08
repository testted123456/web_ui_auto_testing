package com.nonobank.apps.objectRepository;

import java.util.List;

import org.openqa.selenium.*;

public class WebTable extends BaseWebElement {
	
	 private List<WebElement> rows ;
	 
	 private List<WebElement> columns ;
	 

	 public WebTable(WebDriver driver, String xpath){
		 super(driver, xpath);
		 rows=this.getWebElement().findElements(By.tagName("tr"));
		 columns=this.getWebElement().findElements(By.tagName("td"));
	 }

    public int getTotalRowNum(){
        return rows.size();
    }

    public int getTotalColumnNum(){
        return columns.size();
    }

    public WebElement getRowByIndex(int i){
        return rows.get(i);
    }

    public List<WebElement> getRows() {
        return rows;
    }

    public void setRows(List<WebElement> rows) {
        this.rows = rows;
    }

    public List<WebElement> getColumns() {
        return columns;
    }

    public void setColumns(List<WebElement> columns) {
        this.columns = columns;
    }

    /**
     * 返回table的某个cell值
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public String getCellValue(int rowIndex,int columnIndex){
        List<WebElement> column = rows.get(rowIndex).findElements(By.tagName("td"));
        return column.get(columnIndex).getText().trim();
    }
    
	public String getText(){
		return super.getWebElement().getText();
	}
}
