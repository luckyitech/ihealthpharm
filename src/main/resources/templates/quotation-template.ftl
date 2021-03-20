<html>
<head>
    <style>
        .ii a[href] {
           
            color:#5bb4ba !important;
        }

        .a10 .aQH {
            display: none !important;
        }
        table {
            width:100% !important;
        }
       
    </style>


</head>
<body text="#000000">
    <div align="center">
        <br />
        <div>
                     <div  bgcolor="#FFFFFF" style="background-color:#5bb4ba;min-height: 2em;padding-top:9px;width: 750px;">
                       
                            <div style="text-align: center;color: white;">
                                 
                                 <div>${pharmacyName}</div>
                                 <div>${pharmaAddress1}</div>
                                 <div>${pharmaAddress2}</div>
                                  <div>Pin No : ${pinNo}</div>
                              	 <div>Mob : ${mobileOne}</div>
                              		
                            </div>
                    
                       
                       
<table style="font-family: Arial, Helvetica, sans-serif; color: #666666; font-size: 10px; border: 1px solid #1A4A5A;padding-right: 5%; padding-left: 5%;margin-right: auto; margin-left: auto;max-width: 750px;background-color: #5bb4ba;"
            width="700" cellspacing="0" cellpadding="10" align="center" bgcolor="#FFFFFF ">

<tr style="font-size:12px">
                                 
                                 <td>Quotation No:${quotationNo}</td>
                                <td>QTN Dt:${quotationDate}</td>
                              		<td>Requested By:${requestedBy}</td>
                            </tr>
                           
                            
                            

<tr style="font-size:10px">

<th >Item Name</th>
<th>Quantity</th>
<th>Formulation</th>
<th>Manufacturer</th>
<th>Description</th>
</tr>

<#list quotItemModel as index>
   
<tr>

<td >${index.item.itemName}</td>
<td >${index.quantity}</td>
<td >${index.item.itemForm.form}</td>
<td >${index.item.manufacturer.name}</td>
<td >${description}</td>
</tr>
</#list>
</table>
                    </div>
                </div>
                
        
               <div style="background-color:#5bb4ba;width: 750px;">
                    <div style="color: #ffffff; padding: 5px; font-size: 10px;"  align="center">
                    
                    <div>Powered by Ihealth Pharm</div>
                    <div>TO PLACE ORDER USE OUR</div>
                    <div>WHATSAPP NO :${mobileOne}</div>
                    
                        <span style="color: rgb(255, 255, 255); font-family: Verdana; font-size: 10px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: normal; orphans: 2; text-align: -webkit-center; text-indent: 0px; text-transform: none; white-space: normal; windows: 2; word-spacing: 0px; -webkit-text-size-adjust: auto; -webkit-text-stroke-width: 0px; background-color: ##5bb4ba; display: inline !important; float: none; color: white;">© 2020 <a style="color: #fff;" href="www.ihealthpharm.com" target="_blank">www.ihealthpharm.com</a> All rights reserved.</span>
                   </div>
                </div>
                
        <p>
            &nbsp;
        </p>
    </div>
</body>
</html>
                                                    