<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>������֧��</title>
</head>
<body >
	<form action="https://gateway.gopay.com.cn/Trans/WebClientAction.do"
		method="post" id="pay" name="pay">
		<table width="100%">
			<tr>
				<td width="40%" align=right>md5�������Ĵ���ֵ����Ҫ����</td>
				<td align=center>"${plain}</td>
			</tr>
			<tr>
				<td width="40%" align=right>�汾��(version):</td>
				<td align=center><input type="text" id="version" name="version"
					value="${version}" size="50" readonly="readonly" />
				</td>
			</tr>

			<tr>
				<td width="40%" align=right>�ַ���(charset):</td>
				<td align=center><input type="text" id="charset" name="charset"
					value="${charset}" size="50" readonly="readonly" /> <br />
					1:GBK,2:UTF-8</td>
			</tr>

			<tr>
				<td width="40%" align=right>��������(language)��</td>
				<td align=center><input type="text" id="language"
					name="language" value="${language}" size="50" /> <br />1:ZH,2:EN</td>
			</tr>
			<tr>
				<td width="40%" align=right>���ܷ�ʽ(signType)��</td>
				<td align=center><input type="text" id="signType"
					name="signType" value="${signType}" size="50" /> <br />1:MD5,2:SHA</td>
			</tr>

			<tr>
				<td width="40%" align=right>���״���(tranCode):</td>
				<td align=center><input type="text" id="tranCode"
					name="tranCode" value="${tranCode}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>�̻�ID(merchantID):</td>
				<td align=center><input type="text" id="merchantID"
					name="merchantID" value="${merchantID}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>������(merOrderNum):</td>
				<td align=center><input type="text" id="merOrderNum"
					name="merOrderNum" value="${merOrderNum}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>���׽��(tranAmt):</td>
				<td align=center><input type="text" id="tranAmt" name="tranAmt"
					value="${tranAmt}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>������(feeAmt):</td>
				<td align=center><input type="text" id="feeAmt" name="feeAmt"
					value="${feeAmt}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>����(currencyType):</td>
				<td align=center><input type="text" id="currencyType"
					name="currencyType" value="${currencyType}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>�̻�����ҳ���ַ (frontMerUrl):</td>
				<td align=center><input type="text" id="frontMerUrl"
					name="frontMerUrl" value="${frontMerUrl}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>�̻���̨֪ͨ��ַ(backgroundMerUrl):</td>
				<td align=center><input type="text" id="backgroundMerUrl"
					name="backgroundMerUrl" value="${backgroundMerUrl}" size="50" />
				</td>
			</tr>


			<tr>
				<td width="40%" align=right>����ʱ��(tranDateTime):</td>
				<td align=center><input type="text" id="tranDateTime"
					name="tranDateTime" value="${tranDateTime}" size="50" />
				</td>
			</tr>

			<tr>
				<td width="40%" align=right>������ת���˻���virCardNoIn��:</td>
				<td align=center><input type="text" id="virCardNoIn"
					name="virCardNoIn" value="${virCardNoIn}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>�û������IP��tranIP��:</td>
				<td align=center><input type="text" id="tranIP" name="tranIP"
					value="${tranIP}" size="50" />
				</td>
			</tr>


			<tr>
				<td width="40%" align=right>�����Ƿ������ظ��ύ��isRepeatSubmit��:</td>
				<td align=center><input type="text" id="isRepeatSubmit"
					name="isRepeatSubmit" value="${isRepeatSubmit}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>��Ʒ���ƣ�goodsName��:</td>
				<td align=center><input type="text" id="goodsName"
					name="goodsName" value="${goodsName}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>��Ʒ���飨goodsDetail��:</td>
				<td align=center><input type="text" id="goodsDetail"
					name="goodsDetail" value="${goodsDetail}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>��������buyerName��:</td>
				<td align=center><input type="text" id="buyerName"
					name="buyerName" value="${buyerName}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>����ϵ��ʽ��buyerContact��:</td>
				<td align=center><input type="text" id="buyerContact"
					name="buyerContact" value="${buyerContact}" size="50" />
				</td>
			</tr>

			<tr>
				<td width="40%" align=right>�̻�������Ϣ�ֶ�1��merRemark1��:</td>
				<td align=center><input type="text" id="merRemark1"
					name="merRemark1" value="${merRemark1}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>�̻�������Ϣ�ֶ�2��merRemark2��:</td>
				<td align=center><input type="text" id="merRemark2"
					name="merRemark2" value="${merRemark2}" size="50" />
				</td>
			</tr>

			<tr>
				<td width="40%" align=right>���д��루bankCode��:</td>
				<td align=center><input type="text" id="bankCode"
					name="bankCode" value="${bankCode}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>�û����ͣ�userType��:</td>
				<td align=center><input type="text" id="userType"
					name="userType" value="${userType}" size="50" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>MD5���ܱ���(signValue):</td>
				<td align=center><input type="text" id="signValue"
					name="signValue" value="${signValue}" size="50" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td width="40%" align=right>������������ʱ��(ʱ���������):</td>
				<td align=center><input type="text" id="gopayServerTime"
					name="gopayServerTime" value="${gopayServerTime}" size="50"
					readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=2 width="100%">
					<center>
						<input type="button" name="submit_btn" id="submit_btn" value="�ύ����" />
					</center></td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
window.onload = function() {
	document.getElementById("pay").submit();
}

</script>
</html>