<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : uniInfo.xsl
    Created on : July 14, 2017, 2:32 PM
    Author     : Temporary
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <div class="table-container">
            <xsl:apply-templates select="university" />
        </div>
    </xsl:template>
    <xsl:template name="serializeNodeToString">
        <xsl:param name="node" />
        <xsl:variable name="name" select="name($node)" />

        <xsl:text>&lt;</xsl:text>
        <xsl:value-of select="$name" />
        <xsl:for-each select="$node/@*">
            <xsl:text> </xsl:text>
            <xsl:value-of select="name()" />
            <xsl:text>=&quot;</xsl:text>
            <xsl:value-of select="." /> 
            <xsl:text>&quot;</xsl:text>
            <xsl:text> </xsl:text>
        </xsl:for-each>
        <xsl:text>&gt;</xsl:text>
        <xsl:value-of select="./text()" />
        <xsl:for-each select="$node/*">
            <xsl:call-template name="serializeNodeToString">
                <xsl:with-param name="node" select="."/>
            </xsl:call-template>
        </xsl:for-each>

        <xsl:text>&lt;/</xsl:text>
        <xsl:value-of select="$name" />
        <xsl:text>&gt;</xsl:text>
    </xsl:template>
    
<!--    <xsl:template match="blockOfMajors">
        <xsl:text>&lt;</xsl:text>
        <xsl:value-of select="name()"/>
        <xsl:apply-templates select="@*" mode="serialize" />
        <xsl:choose>
            <xsl:when test="node()">
                <xsl:text>&gt;</xsl:text>
                <xsl:apply-templates mode="serialize" />
                <xsl:text>&lt;/</xsl:text>
                <xsl:value-of select="name()"/>
                <xsl:text>&gt;</xsl:text>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text> /&gt;</xsl:text>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="@*" mode="serialize">
        <xsl:text> </xsl:text>
        <xsl:value-of select="name()"/>
        <xsl:text>="</xsl:text>
        <xsl:value-of select="."/>
        <xsl:text>"</xsl:text>
    </xsl:template>

    <xsl:template match="text()" mode="serialize">
        <xsl:value-of select="."/>
    </xsl:template>-->
    <xsl:template match="university">
        <p>
            <xsl:value-of select="universityName"/>
        </p>
        <p>
            Mã trường: <xsl:value-of select="code"/>
        </p>
        <p>
            Số điện thoại: <xsl:value-of select="phoneNumber"/>
        </p>
        <p>
            Website: <xsl:value-of select="website"/>
        </p>
        
        <xsl:choose>
            <xsl:when test="count(majors)>0">
                <div class="pull-right">
                    <button class="btn-custom" onclick="generatePDF()">Tải thông tin trường</button>
                </div>
                <table id="table" class="shadow-z-1 table table-hover table-mc-light-blue">
                    <thead>
                        <tr>
                            <th>Mã ngành</th>
                            <th>Tên ngành</th>
                            <th>Chỉ tiêu năm nay</th>
                            <th>Chỉ tiêu năm trước</th>
                            <th>Điểm sàn năm trước</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="majors/major">
                            <xsl:variable name="count" select="count(blockOfMajors/blockOfMajor[baseScoreLastYear or acceptedEntryLastYear])"/>
                            <!-- data-toggle="false" -->
                            <tr>
                                <td>
                                    <xsl:value-of select="majorCode"/>
                                    <input type="hidden">
                                        <xsl:attribute name="value">
                                            <xsl:call-template name="serializeNodeToString">
                                                <xsl:with-param name="node" select="blockOfMajors"/>
                                            </xsl:call-template>
                                            <!--<xsl:apply-templates select="blockOfMajors"/>-->
                                        </xsl:attribute>
                                    </input>
                                </td>
                                <td>
                                    <xsl:value-of select="majorName"/>
                                </td>
                                <td>
                                    <xsl:choose>
                                        <xsl:when test="string-length(examEntryQuantity) > 0">
                                            <xsl:value-of select="examEntryQuantity"/>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            Không tuyển
                                        </xsl:otherwise>
                                    </xsl:choose>
                                    
                                </td>
                                <td>
                                    <xsl:choose>
                                        <xsl:when test="string-length(lastYearEntryQuantity) > 0">
                                            <xsl:value-of select="lastYearEntryQuantity"/>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            Không tuyển
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </td>
                                <td>
                                    <xsl:choose>
                                        <xsl:when test="string-length(gradeToPass) > 0">
                                            <xsl:value-of select="format-number(gradeToPass,'##0.000')"/>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            Không có
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </xsl:when>
            <xsl:otherwise>
                
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>