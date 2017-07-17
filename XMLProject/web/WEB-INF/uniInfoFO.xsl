<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : uniInfoFO.xsl
    Created on : July 12, 2017, 4:11 PM
    Author     : Temporary
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" >
            <fo:layout-master-set>
                <fo:simple-page-master master-name="master-page" page-width="8in" 
                                       page-height="11in" margin-top="36pt" 
                                       margin-bottom="36pt" margin-right="72pt" margin-left="72pt">
                    <fo:region-body margin-top="3.5cm" margin-bottom="2.5cm"/>
                    <fo:region-before extent="2.0cm"/>
                    <fo:region-after extent="2.0cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="master-page" >
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block font-family="Arial" font-size="14pt" font-weight="bold">                       
                        <fo:inline> 
                            <fo:external-graphic width="50px" height="50px" >
                                <xsl:attribute name="src">
                                    <xsl:value-of select="concat($pathFile,p:data/p:logo)"/>
                                </xsl:attribute>
                            </fo:external-graphic>
                        </fo:inline>
                        <fo:inline font-weight="bold" font-size="14pt" font-family="Arial" >
                            <fo:leader leader-length="0pt" /> 
                            Phòng khám <xsl:value-of select="p:data/p:clinicName"/> 
                        </fo:inline>
                    </fo:block> 
                    <fo:block margin-left="50px" font-family="Arial"  font-size="14pt" color="BLUE">
                        <fo:inline>
                            <fo:leader leader-length="0pt" /> 
                            Địa chỉ <xsl:value-of select="p:data/p:doctor/p:address"/>
                        </fo:inline> 
                    </fo:block>                   
                    <fo:block font-family="Arial"  margin-left="50px" color="BLUE">
                        Điện thoại <xsl:value-of select="p:data/p:doctor/p:phone"/>
                    </fo:block>
                    <fo:block font-size="12pt" font-family="Arial" text-align="right">
                        <fo:inline>
                            Mã sô bệnh <xsl:value-of select="p:data/p:patient/p:id"/>
                        </fo:inline> 
                    </fo:block>
                </fo:static-content>
                <fo:static-content flow-name="xsl-region-after"> 
                    <fo:block font-size="12pt" text-align="left" font-family="Arial">
                        Bác sĩ khám bệnh:
                    </fo:block>
                    <fo:block font-size="12pt" text-align="right" font-family="Arial">
                        Ngày khám:  <xsl:value-of select="p:data/p:examinationDay"/> 
                    </fo:block>
                    <fo:block text-align="left" margin-left="10px" margin-top="40px" font-family="Arial" text-decoration="underline">
                        <xsl:value-of select="p:data/p:doctor/p:name"/> 
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="18pt" font-family="Arial"  margin-top="40px"
                              text-align="center">
                        TOA THUỐC
                    </fo:block>
                    <fo:block font-family="Arial" font-size="10.5pt"  line-height="1.7205" font-weight="bold">
                        <fo:inline font-weight="bold" font-size="10.5pt">
                            <fo:leader leader-length="0pt"/>
                            Tên bệnh nhân: <xsl:value-of select="p:data/p:patient/p:name"/>
                        </fo:inline> 
                    </fo:block>
                    <fo:block  font-family="Arial" font-size="10.5pt"
                               line-height="2" font-weight="bold">
                        <fo:inline font-weight="bold" font-size="10.5pt">
                            <fo:leader leader-length="0pt"/>
                            Giới tính: <xsl:value-of select="p:data/p:patient/p:sex"/>
                        </fo:inline>  
                        <fo:inline font-weight="bold" font-size="10.5pt">
                            <fo:leader leader-length="0pt"/>
                            Tuổi: <xsl:value-of select="p:data/p:patient/p:age"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Arial" font-size="10.5pt" line-height="2" font-weight="bold">
                        <fo:inline font-weight="bold" font-size="10.5pt">
                            <fo:leader leader-length="0pt"/>
                            Địa chỉ: <xsl:value-of select="p:data/p:patient/p:address"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Arial" font-size="10.5pt" line-height="1.7205" font-weight="bold">
                        <fo:inline font-weight="bold" font-size="10.5pt">
                            <fo:leader leader-length="0pt"/>
                            Chẩn đoán: <xsl:value-of select="p:data/p:prediction"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Arial" font-size="10.5pt" line-height="1.7205" font-weight="bold">
                        <fo:inline font-weight="bold" font-size="10.5pt">
                            <fo:leader leader-length="0pt"/>
                            Chỉ định uống thuốc
                        </fo:inline>
                    </fo:block>
                    <fo:table  start-indent="0pt" border-top-style="solid" 
                               border-top-color="#000080" border-top-width="0.75pt" 
                               border-left-style="solid" border-left-color="#000080" 
                               border-left-width="0.75pt" border-bottom-style="solid"
                               border-bottom-color="#000080" border-bottom-width="0.75pt" table-layout="auto"
                               border-right-style="solid" border-right-color="#000080" border-right-width="0.75pt">
                        <fo:table-column column-number="1" column-width="24.9pt"/>
                        <fo:table-column column-number="2" column-width="130.85pt"/>
                        <fo:table-column column-number="3" column-width="78.5pt"/>
                        <fo:table-column column-number="4" column-width="78.5pt"/>
                        <fo:table-column column-number="5" column-width="78.5pt"/>
                        <fo:table-column column-number="6" column-width="109.15pt"/>
                        <fo:table-body start-indent="0pt" end-indent="0pt">
                            <fo:table-row  background-color="blue" font-family="Arial" >
                                <fo:table-cell padding-top="0pt" padding-left="5.4pt" padding-bottom="0pt" 
                                           padding-right="5.4pt" border-left-style="solid" border-right-style="solid"
                                           border-left-color="#000080" border-right-color="#000080" border-left-width="0.375pt" 
                                           border-right-width="0.375pt" border-top-style="solid" border-bottom-style="solid" 
                                           border-top-color="#000080" border-bottom-color="#000080" border-top-width="0.375pt" 
                                           border-bottom-width="0.375pt">
                                    <fo:block>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding-top="0pt" padding-left="5.4pt" padding-bottom="0pt" padding-right="5.4pt" border-left-style="solid" border-right-style="solid" border-left-color="#000080" border-right-color="#000080" border-left-width="0.375pt" border-right-width="0.375pt" border-top-style="solid" border-bottom-style="solid" border-top-color="#000080" border-bottom-color="#000080" border-top-width="0.375pt" border-bottom-width="0.375pt"  >
                                    <fo:block font-size="10.5pt" line-height="1.7205" font-weight="bold" color="#FFFFFF"> 
                                        Tên thuốc 
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding-top="0pt" padding-left="5.4pt" padding-bottom="0pt" padding-right="5.4pt" border-left-style="solid" border-right-style="solid" border-left-color="#000080" border-right-color="#000080" border-left-width="0.375pt" border-right-width="0.375pt" border-top-style="solid" border-bottom-style="solid" border-top-color="#000080" border-bottom-color="#000080" border-top-width="0.375pt" border-bottom-width="0.375pt"  >
                                    <fo:block  font-size="10.5pt" line-height="1.7205" font-weight="bold" color="#FFFFFF">
                                        Uống sáng 
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding-top="0pt" padding-left="5.4pt" padding-bottom="0pt" padding-right="5.4pt" border-left-style="solid" border-right-style="solid" border-left-color="#000080" border-right-color="#000080" border-left-width="0.375pt" border-right-width="0.375pt" border-top-style="solid" border-bottom-style="solid" border-top-color="#000080" border-bottom-color="#000080" border-top-width="0.375pt" border-bottom-width="0.375pt"  >
                                    <fo:block font-size="10.5pt" line-height="1.7205" font-weight="bold" color="#FFFFFF">  
                                        Uống trưa 
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding-top="0pt" padding-left="5.4pt" padding-bottom="0pt" padding-right="5.4pt" border-left-style="solid" border-right-style="solid" border-left-color="#000080" border-right-color="#000080" border-left-width="0.375pt" border-right-width="0.375pt" border-top-style="solid" border-bottom-style="solid" border-top-color="#000080" border-bottom-color="#000080" border-top-width="0.375pt" border-bottom-width="0.375pt"  >
                                    <fo:block  font-size="10.5pt" line-height="1.7205" font-weight="bold" color="#FFFFFF"> 
                                        Uống tối 
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding-top="0pt" padding-left="5.4pt" padding-bottom="0pt" padding-right="5.4pt" border-left-style="solid" border-right-style="solid" border-left-color="#000080" border-right-color="#000080" border-left-width="0.375pt" border-right-width="0.375pt" border-top-style="solid" border-bottom-style="solid" border-top-color="#000080" border-bottom-color="#000080" border-top-width="0.375pt" border-bottom-width="0.375pt"  >
                                    <fo:block font-size="10.5pt" line-height="1.7205" font-weight="bold" color="#FFFFFF">
                                        Uống trong vòng 
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <xsl:for-each select="p:data/p:medicines/p:medicine">
                                <fo:table-row font-family="Arial">
                                    <fo:table-cell padding-top="0pt" padding-left="5.4pt" padding-bottom="0pt" padding-right="5.4pt" border-left-style="solid" border-right-style="solid" border-left-color="#000080" border-right-color="#000080" border-left-width="0.375pt" border-right-width="0.375pt" border-top-style="solid" border-bottom-style="solid" border-top-color="#000080" border-bottom-color="#000080" border-top-width="0.375pt" border-bottom-width="0.375pt" >
                                        <fo:block>
                                            <xsl:number level="single" count="p:medicine"/>.</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding-top="0pt" padding-left="5.4pt" padding-bottom="0pt" padding-right="5.4pt" border-left-style="solid" border-right-style="solid" border-left-color="#000080" border-right-color="#000080" border-left-width="0.375pt" border-right-width="0.375pt" border-top-style="solid" border-bottom-style="solid" border-top-color="#000080" border-bottom-color="#000080" border-top-width="0.375pt" border-bottom-width="0.375pt" >
                                        <fo:block  line-height="1.7205" font-weight="bold">
                                            <xsl:value-of select="p:name"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding-top="0pt" padding-left="5.4pt" padding-bottom="0pt" padding-right="5.4pt" border-left-style="solid" border-right-style="solid" border-left-color="#000080" border-right-color="#000080" border-left-width="0.375pt" border-right-width="0.375pt" border-top-style="solid" border-bottom-style="solid" border-top-color="#000080" border-bottom-color="#000080" border-top-width="0.375pt" border-bottom-width="0.375pt">
                                        <fo:block line-height="1.7205" font-weight="bold"> 
                                            <xsl:value-of select="p:morning"/> viên 
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding-top="0pt" padding-left="5.4pt" padding-bottom="0pt" padding-right="5.4pt" border-left-style="solid" border-right-style="solid" border-left-color="#000080" border-right-color="#000080" border-left-width="0.375pt" border-right-width="0.375pt" border-top-style="solid" border-bottom-style="solid" border-top-color="#000080" border-bottom-color="#000080" border-top-width="0.375pt" border-bottom-width="0.375pt">
                                        <fo:block line-height="1.7205" font-weight="bold">  
                                            <xsl:value-of select="p:lunch"/> viên </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding-top="0pt" padding-left="5.4pt" padding-bottom="0pt" padding-right="5.4pt" border-left-style="solid" border-right-style="solid" border-left-color="#000080" border-right-color="#000080" border-left-width="0.375pt" border-right-width="0.375pt" border-top-style="solid" border-bottom-style="solid" border-top-color="#000080" border-bottom-color="#000080" border-top-width="0.375pt" border-bottom-width="0.375pt">
                                        <fo:block line-height="1.7205" font-weight="bold">  
                                            <xsl:value-of select="p:night"/> viên </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell padding-top="0pt" padding-left="5.4pt" padding-bottom="0pt" padding-right="5.4pt" border-left-style="solid" border-right-style="solid" border-left-color="#000080" border-right-color="#000080" border-left-width="0.375pt" border-right-width="0.375pt" border-top-style="solid" border-bottom-style="solid" border-top-color="#000080" border-bottom-color="#000080" border-top-width="0.375pt" border-bottom-width="0.375pt">
                                        <fo:block line-height="1.7205" font-weight="bold">  
                                            <xsl:value-of select="p:during"/> ngày </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                    <fo:block font-family="Arial" margin-top="20px" line-height="1.7205" font-size="16pt"
                              font-weight="bold">Lời khuyên
                    </fo:block>
                    <fo:list-block font-family="Arial" > 
                        <xsl:for-each select="p:data/p:advices/p:advice">
                            <fo:list-item>
                                <fo:list-item-label>
                                    <fo:block>*</fo:block>
                                </fo:list-item-label>
                                <fo:list-item-body margin-left="20px">
                                    <fo:block>
                                        <xsl:value-of select="."/>
                                    </fo:block>
                                </fo:list-item-body>
                            </fo:list-item>
                        </xsl:for-each>
                        <fo:list-item>
                            <fo:list-item-label>
                                <fo:block></fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body margin-left="20px">
                                <fo:block>---------------</fo:block>
                            </fo:list-item-body>
                        </fo:list-item>
                    </fo:list-block> 
                    <fo:block margin-top="20px" line-height="1.7205" font-size="13pt"
                              font-weight="bold">Ngày tái khám: <xsl:value-of select="p:data/p:reExamination"/>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence> 
        </fo:root>
    </xsl:template>

</xsl:stylesheet>
