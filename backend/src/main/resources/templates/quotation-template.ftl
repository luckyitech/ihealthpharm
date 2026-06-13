<html>
<head>
    <style>
        .ii a[href] {
           
            color:#ffffff !important;
        }

        .a10 .aQH {
            display: none !important;
        }
        table {
            width:100% !important;
        }
        .a3s, .g6 {
    font: small/ 1.5 Arial,Helvetica,sans-serif;
    }
       
    </style>


</head>
<body text="#000000">
    <div align="center">
        <br />
        <div>
                     <div  bgcolor="#FFFFFF" style="background-color:#ffffff;min-height: 2em;padding-top:9px;width: 750px;">
                       
                            <div style="text-align: center;color: #202124;font-weight:bold">
                                 
                                 <div>${pharmacyName}</div>
                                 <div>${pharmaAddress1}</div>
                                 <div>${pharmaAddress2}</div>
                                  <div>Pin No : ${pinNo}</div>
                              	 <div>Mob : ${mobileOne}</div>
                              		
                            </div>
                    
                       
                       
<table style="font-family: Arial, Helvetica, sans-serif; color: #202124; font-size: 10px;padding-right: 5%; padding-left: 5%;margin-right: auto; margin-left: auto;max-width: 1000px;background-color: #ffffff;"
            cellspacing="0" cellpadding="10" align="center" bgcolor="#FFFFFF ">

<tr style="font-size:12px;font-weight:bold">
                                 
                                 <td>Quotation No:</td>
                                 <td>${quotationNo}</td>
                                <td>QTN Dt:</td>
                                <td>${quotationDate}</td>
                              		<td>Requested By:</td>
                              			<td>${requestedBy}</td>
                            </tr>
                           
                            
 </table>
 
 <table style="font-family: Arial, Helvetica, sans-serif; color: #202124; font-size: 12px; border: 1px solid black;border-collapse: collapse;padding-right: 5%; padding-left: 5%;margin-right: auto; margin-left: auto;max-width: 750px;background-color: #ffffff;"
            width="700" cellspacing="0" cellpadding="10" align="center" bgcolor="#FFFFFF ">    

<tr style="font-size:12px">

<th >S.No</th>
<th >Item Name</th>
<th>Quantity</th>
<th>Bonus</th>
<th>Unit Price</th>
<th>Disc%</th>
<th>Disc Amt</th>
<th>Vat%</th>
<th>Vat Amt</th>
<th>Total Amt</th>
</tr>

<#list quotItemModel as index>
   
<tr>
<td>${index?counter}</td>
<td >${index.item.itemName}</td>
<td >${index.quantity}</td>
<td >${index.bonus}</td>
<td >0</td>
<td >0</td>
<td >0</td>
<td >0</td>
<td >0</td>
<td >0</td>
<!--<td >${index.unitPurchasePrice}</td>
<td>${index.discountPercentage}</td>
<td>${index.quantity*index.unitPurchasePrice*index.discountPercentage/100}</td>
<td>${index.tax.categoryValue}</td>
<td >${(index.quantity*index.unitPurchasePrice)*(1-index.discountPercentage/100)*(index.tax.categoryValue/100)}</td>
<td >${index.quantity*index.unitPurchasePrice*(1-index.discountPercentage/100)*(1+index.tax.categoryValue/100)}</td>-->

</tr>
</#list>

</table>

<table style="padding-top:40px;font-family: Arial, Helvetica, sans-serif; color: #202124; font-size: 12px;">
<tr><td>Remarks : ${remarks}</td></tr>

<tr><td>Terms & Conditions :</td></tr>
</table>

                    </div>
                </div>
                
        
               <div style="background-color:#ffffff;width: 750px;">
                    <div style=" color: #202124;padding: 5px; font-size: 11px;font-weight:bold"  align="center">
                    
                    <div>Powered by Ihealth Pharm</div>
                    <div>TO PLACE ORDER USE OUR</div>
                    <div>WHATSAPP NO :${mobileOne}</div>
                    
                        <span style="color: rgb(255, 255, 255); font-family: Verdana; font-size: 10px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: normal; orphans: 2; text-align: -webkit-center; text-indent: 0px; text-transform: none; white-space: normal; windows: 2; word-spacing: 0px; -webkit-text-size-adjust: auto; -webkit-text-stroke-width: 0px; background-color: ##5bb4ba; display: inline !important; float: none; color: white;">© 2020 <a  href="www.ihealthpharm.com" target="_blank">www.ihealthpharm.com</a> All rights reserved.</span>
                   </div>
                </div>
                
        <p>
            &nbsp;
        </p>
    </div>
</body>
</html>
                                                    