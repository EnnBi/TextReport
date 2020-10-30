package com.aaratech.leasing.report;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaratech.leasing.report.TemplateController.Format;
import com.aaratech.leasing.report.entity.Key;
import com.aaratech.leasing.report.entity.MainFooter;
import com.aaratech.leasing.report.entity.MainHeader;
import com.aaratech.leasing.report.entity.PageFooter;
import com.aaratech.leasing.report.entity.TableHeader;
import com.aaratech.leasing.report.entity.Template;
import com.aaratech.leasing.report.repo.TemplateDao;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReportGeneratorService {

		int pageNo=1;
		int lineCount=0;
		int pageLines = 0;
		int maxWidth=0;
		int sNo=0;
		String tempLine;
		String companyName;
		String className ="";
		String companyLabel;
		String classLabel="";

		enum Type{LABEL,VALUE,DATE,PAGE}
		enum Alignment{RIGHT,LEFT,CENTER}


		@Autowired
		TemplateDao templateDao;


		public void generateReport(String data) throws NoSuchFieldException, SecurityException {


			//String fileName = "D:\\reports_output\\Leasing_Report.txt";
			String fileName = "D:\\nadeem\\mywork\\docs\\newFile.txt";
			File fl = new File(fileName);
			System.err.println("Deleting file :"+fl.delete());
			RandomAccessFile file = null;

			try {
				file = new RandomAccessFile(new File(fileName), "rw");
			} catch (IOException e) {
				e.printStackTrace();
			}

			ObjectMapper mapper = new ObjectMapper();
			lineCount=pageLines=maxWidth=sNo=0;
			pageNo=1;
			tempLine=null;
			companyName=null;
			className ="";
			companyLabel=null;
			classLabel="";

			try {

				Map<String, Object> map  = mapper.readValue(data, new TypeReference<Map<String, Object>>(){});
				Template template = templateDao.findByName((String)map.get("class"));
				pageLines=template.getPageLines();
				maxWidth=template.getMaxWidth();
				int index=0;
				addHeader(file, map,template,index);
				addTableHeader(template, file);
				addTableData(map, template, file);
				addFooter(template, file, map);
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	public int checkLineLength(String str,int width){
		if(str.length()>width){
			int position = str.lastIndexOf(" ");
			str =  str.substring(0, position) + System.lineSeparator() + str.substring(position);
			return 1;
		}
		return 0;
	}




	public void addHeader(RandomAccessFile file,Map<String, Object> map, Template template, int index) throws IOException{

		int maxRow = templateDao.findMaxRowOfMainHeaderOnTemplateId(template.getId());
		List<MainHeader> mainHeaders =  template.getMainHeader();
		for(int i=1;i<=maxRow;i++){
			String line ="";
			final int temp=i;
			List<MainHeader> maiHeaderWithSameRow = mainHeaders.stream().filter(mh->mh.getRow()==temp).collect(Collectors.toList());
			for(MainHeader header:maiHeaderWithSameRow){
				if(header.isDashed()){
					line = StringUtils.rightPad("",template.getMaxWidth(),"-");
				}
				else if(header.isNewLine()){
					line="";
				}
				else{
					boolean hasSize = ((List<Map<String, String>>)map.get("details")).size()>0;
					if(header.isCompany() ){
						if(hasSize){
							companyName = ((List<Map<String, String>>)map.get("details")).get(index).get(header.getName());
							companyLabel=header.getName();
							map.put(header.getName(),companyName);
						}
					}
					if(header.isClass()){
						if(hasSize){
							className=((List<Map<String, String>>)map.get("details")).get(index).get(header.getName());
							classLabel=header.getName();
							map.put(header.getName(), className);
						}
					}
					if(header.getType().equals(Type.DATE.toString())){
					  if(hasSize){	
						map.put(header.getName(), ((List<Map<String, String>>)map.get("details")).get(index).get(header.getName()));
					  }
					}
					line = appendToLine(header, line, map, template.getDateFormat());
				}
			}
			line = line.concat(System.lineSeparator());
			writeToFile(file, line, file.length());
			lineCount++;
		}
	}


	public void addTableHeader(Template template,RandomAccessFile file) throws IOException{
		int lineNo=1;
		String line="";
		List<TableHeader> headers = templateDao.findHeadersOnTemplateId(template.getId());
		template.setTableHeaders(headers);
		for (TableHeader header:template.getTableHeaders()) {
			int length=line.length();
			if(isWidthGreater(length+header.getWidth(),maxWidth,lineNo)>lineNo){
				line = line.concat(System.lineSeparator());
				lineNo++;
				lineCount++;
			}
			
			line = line+paddedText(header.getValue(), header.getAlignment(), header.getWidth());
		}
		line=line.concat(System.lineSeparator());
		lineCount ++;
		writeToFile(file, line, file.length());
		line=StringUtils.rightPad("",template.getMaxWidth(),"-");
		line=line.concat(System.lineSeparator());
		lineCount +=2;
		writeToFile(file, line, file.length());

	}

	public void addTableData(Map<String,Object> map,Template template,RandomAccessFile file) throws IOException{
		@SuppressWarnings("unchecked")
		List<Map<String,String>> details = (List<Map<String, String>>) map.get("details");
		List<Key> keys = templateDao.findKeysOnTemplateId(template.getId());
		Map<String,Double> propertySumMap = new HashMap<>();
		String line="";
		if(details.size()>0){
		for (int i=0;i<details.size();i++) {
			sNo++;
			String serialNo=sNo+".";
			String serialPadding = StringUtils.rightPad("",template.getTableHeaders().get(0).getWidth());
			List<String> linesList = new ArrayList<String>();
			List<String> splitedValues = new ArrayList<>();
			String currentLine="";
			for(Key key : keys){
				String value=String.valueOf(details.get(i).get(key.getValue()));
				if(key.isDoSum()){
					Double sum = propertySumMap.getOrDefault(key.getValue(),0.0)+
							Double.valueOf(value);
					propertySumMap.put(key.getValue(),sum);
					Double total = propertySumMap.getOrDefault(key.getValue()+"_TOTAL", 0.0)+Double.valueOf(value);
					propertySumMap.put(key.getValue()+"_TOTAL", total);
				}
				if(key.getFormat() != null && !key.getFormat().isEmpty()){
					value=	format(value,key.getFormat());
				}

				splitedValues=Arrays.asList(value.split("(?<=\\G.{"+(key.getWidth()-2)+"})"));
				boolean newLine=false;
				int index=0;
				int firstLineLength=0;

				for(int j=0;j<splitedValues.size();j++){
					if(currentLine.length()+key.getWidth()>maxWidth){
						newLine=true;
					}

					if(newLine)
						index=linesList.size();

					String toBeAddedLine =linesList.size()-1 >=index?linesList.get(index):!newLine?serialPadding:"";
					if(j==0)
						firstLineLength=toBeAddedLine.length();

					toBeAddedLine=StringUtils.rightPad(toBeAddedLine, firstLineLength);
					int length=toBeAddedLine.length();
					toBeAddedLine = toBeAddedLine.concat(paddedText(splitedValues.get(j),key.getAlignment(),key.getWidth()));
					toBeAddedLine = StringUtils.rightPad(toBeAddedLine,length+key.getWidth());

					if(linesList.size()>index)
						linesList.set(index, toBeAddedLine);
					else
						linesList.add(index, toBeAddedLine);
					index++;
					currentLine=toBeAddedLine;
				}

			}

			if(checkIfPageSizeReached(template, linesList.size()+1)){
				List<String> rowKeys = templateDao.findNameOfRowkey(template.getId());
				String key="";
				String value="";
				if(!rowKeys.isEmpty()){
					key=rowKeys.get(0);
					double sum = propertySumMap.get(key);
					value= String.valueOf(details.get(i).get(key));
					sum = sum-Double.valueOf(value);
					propertySumMap.put(key, sum);
					addPageFooter(template, file, propertySumMap,true);
				}
				lineCount=0;
				pageNo++;
				addHeader(file, map,template,i);
				addTableHeader(template, file);
				if(!rowKeys.isEmpty())
					propertySumMap.put(key, Double.valueOf(value));
			}


			for (int k=0;k<linesList.size();k++) {
				String lin="";
				if(k==0)
					lin=serialNo+linesList.get(k).substring(serialNo.length());
				else
					lin =linesList.get(k);

				lin = lin.concat(System.lineSeparator());
				lineCount++;
				if(k+1==linesList.size()){
					lin=lin.concat(System.lineSeparator());
					lineCount++;
				}


				writeToFile(file, lin, file.length());
			}

			if(i<details.size()-1){
				String tmpclassName = details.get(i+1).get(classLabel) !=null ? details.get(i+1).get(classLabel) : "";
				if(!companyName.equals(details.get(i+1).get(companyLabel)) || !className.equals(tmpclassName)){
					addPageFooter(template, file, propertySumMap,false);
					int maxHeaderRow = templateDao.findMaxRowOfMainHeaderOnTemplateId(template.getId());
					pageNo++;
					if(lineCount+maxHeaderRow>pageLines){
						int temp=pageLines-lineCount;
						line = StringUtils.rightPad("",temp,System.lineSeparator());
						lineCount=0;
						writeToFile(file, line, file.length());
					}
					else{
						sNo=0;
						lineCount=0;
						addHeader(file, map, template, i+1);
						addTableHeader(template, file);
					}
				}
			}

			if(i+1==details.size())
				addPageFooter(template, file, propertySumMap,false);
		}
		sNo=0;
		}
		else{
			 line=System.lineSeparator()+System.lineSeparator()+paddedText("No Records Found", Alignment.CENTER.toString(),maxWidth); 
			 writeToFile(file, line, file.length());
		}
	}

	public void addPageFooter(Template template,RandomAccessFile file,Map<String,Double> map, boolean onlyPage) throws IOException{
		Integer maxRow=templateDao.findMaxRowOfPageFooterOnTemplateId(template.getId());
		maxRow=maxRow!=null?maxRow:0;
		List<PageFooter> pageFooters=template.getPageFooter();
		for(int i=1;i<=maxRow;i++){
			String line = "";
			final int temp=i;
			List<PageFooter> pageFooterWithSameRow = pageFooters.stream().filter(mh->mh.getRow()==temp).collect(Collectors.toList());
			for(PageFooter footer:pageFooterWithSameRow){
				if(footer.isDashed()){
					line = StringUtils.rightPad("",template.getMaxWidth(),"-");
				}
				else if(footer.isNewLine()){
					line="";
				}else if(onlyPage){
					if(footer.isPage())
						line = appendToLinePageFooter(footer, line, map, template.getDateFormat());
				}
				else
					line = appendToLinePageFooter(footer, line, map, template.getDateFormat());
			}
			line = line.concat(System.lineSeparator());
			writeToFile(file, line, file.length());
			lineCount++;
		}
	}

	public void addFooter(Template template,RandomAccessFile file,Map<String,Object> map) throws IOException{

		Integer maxRow = templateDao.findMaxRowOfMainFooterOnTemplateId(template.getId());
		maxRow=maxRow !=null?maxRow:0;
		List<MainFooter> mainFooters =  template.getMainFooter();
		for(int i=1;i<=maxRow;i++){
			String line ="";
			final int temp=i;
			List<MainFooter> mainFooterWithSameRow = mainFooters.stream().filter(mh->mh.getRow()==temp).collect(Collectors.toList());
			for(MainFooter footer:mainFooterWithSameRow){
				if(footer.isDashed()){
					line = StringUtils.rightPad("",template.getMaxWidth(),"-");
				}
				else if(footer.isNewLine()){
					line="";
				}
				else
					line = appendToLineMainFooter(footer, line, map, template.getDateFormat());
			}
			line = line.concat(System.lineSeparator());
			writeToFile(file, line, file.length());
			lineCount++;
		}
	}

	public boolean checkIfPageSizeReached(Template template,int lineListSize) throws IOException{
		Integer maxPageFooterSize = templateDao.findPageTotalRowsOfPageFooterOnTemplateId(template.getId());
		maxPageFooterSize=maxPageFooterSize==null?0:maxPageFooterSize;
		if(lineCount+lineListSize>pageLines-maxPageFooterSize)
			return true;
		return false;
	}

	public void writeToFile(RandomAccessFile file, String data, long position)
			throws IOException {

		file.seek(position);
		file.write(data.getBytes());

	}

	public int isWidthGreater(int length,int maxWidth,int lineNo){
		switch (lineNo) {
			case 1:
				return length>maxWidth?2:lineNo;
			case 2:
				return length>maxWidth*2?3:lineNo;
			case 3:
				return length>maxWidth*3?4:lineNo;
			default:
				return lineNo;
		}

	}


	int checkOccurenceOfSubstring(String str,String subString){
		int lastIndex = 0;
		int result=0;
		while(lastIndex != -1) {

			lastIndex = str.indexOf(subString,lastIndex);

			if(lastIndex != -1){
				result++;
				lastIndex += 1;
			}
		}
		return result;
	}

	String appendToLine(MainHeader mainHeader,String line,Map<String, Object> map,String dateFormat){
		switch (Type.valueOf(mainHeader.getType())) {
			case LABEL:
				return line+paddedText(mainHeader.getName(), mainHeader.getAlignment(), mainHeader.getWidth());
			case VALUE:
				return line+paddedText(map.getOrDefault(mainHeader.getName(), " ").toString(), mainHeader.getAlignment(), mainHeader.getWidth());
			case DATE:
				return line+paddedText(map.getOrDefault(mainHeader.getName()," ").toString(), mainHeader.getAlignment(), mainHeader.getWidth());
			default:
				return line+paddedText(String.valueOf(pageNo),mainHeader.getAlignment(), mainHeader.getWidth());
		}
	}
	String appendToLinePageFooter(PageFooter pageFooter,String line,Map<String, Double> map,String dateFormat){
		switch (Type.valueOf(pageFooter.getType())) {
			case LABEL:
				return line+paddedText(pageFooter.getName(), pageFooter.getAlignment(), pageFooter.getWidth());
			case VALUE:
				String value=map.getOrDefault(pageFooter.getName(),0.0).toString();
				map.remove(pageFooter.getName());
				if(value!=null &&!value.isEmpty())
					value=format(value, pageFooter.getFormat());
				return line+paddedText(value, pageFooter.getAlignment(), pageFooter.getWidth());
			case DATE:
				return line+paddedText(LocalDate.now()
						.format(DateTimeFormatter.ofPattern(dateFormat)), pageFooter.getAlignment(), pageFooter.getWidth());
			default:
				return line+paddedText(String.valueOf(pageNo),pageFooter.getAlignment(), pageFooter.getWidth());
		}
	}

	String appendToLineMainFooter(MainFooter mainFooter,String line,Map<String, Object> map,String dateFormat){
		switch (Type.valueOf(mainFooter.getType())) {
			case LABEL:
				return line+paddedText(mainFooter.getName(), mainFooter.getAlignment(), mainFooter.getWidth());
			case VALUE:{
				String data=map.getOrDefault(mainFooter.getName(),0.0).toString();
				if(data!=null &&!data.isEmpty())
					data=format(data, mainFooter.getFormat());
				return line+paddedText(data, mainFooter.getAlignment(), mainFooter.getWidth());
			}
			case DATE:
				return line+paddedText(LocalDate.now()
						.format(DateTimeFormatter.ofPattern(dateFormat)), mainFooter.getAlignment(), mainFooter.getWidth());
			default:
				return line+paddedText(String.valueOf(pageNo),mainFooter.getAlignment(), mainFooter.getWidth());
		}
	}

	String paddedText(String text,String aligment,int width){

		switch(Alignment.valueOf(aligment)){
			case RIGHT:{
				text = StringUtils.leftPad(text,width-2);
				return StringUtils.rightPad(text, width);
			}
			case CENTER:{
				int leftPadSize=0;
				int rightPadSize=0;
				if(width%2==0){
					leftPadSize=rightPadSize=(width-text.length())/2;
				}
				else{
					leftPadSize=(width-text.length())/2+1;
					rightPadSize=(width-text.length())/2;

				}
				text=StringUtils.leftPad(text,leftPadSize+text.length());
				text=StringUtils.rightPad(text, rightPadSize+text.length());
				return text;
			}
			default:{
				return StringUtils.rightPad(text,width);
			}
		}
	}
	
	String format(String value,String format){
		switch (Format.valueOf(format)) {
		case AM:
			DecimalFormat df = new DecimalFormat("##,##.##");
			return df.format(Double.parseDouble(value)).toString();
		case AL:
			return String.format("%,.2f",Double.parseDouble(value));
		default:
			return value;
		}
	}
}
