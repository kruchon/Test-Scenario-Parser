package ${generationPackage}

<#list parameterClassNames as parameterClassName>import ${generationPackage}.${parameterClassName}<#if parameterClassName?has_next>${'\n'}</#if></#list>

interface ${subjectClass} {
    <#list kotlinMethods as kotlinMethod>
    fun ${kotlinMethod.name}(${kotlinMethod.parameterName}: ${kotlinMethod.parameterClassName})
    </#list>
}