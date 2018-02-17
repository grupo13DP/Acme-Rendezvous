<%--
  Created by IntelliJ IDEA.
  User: khawla
  Date: 17/02/2018
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="creator/rendezvous/edit.do" modelAttribute="rendezvous">

    <form:hidden path="id"/>
    <form:hidden path="version"/>
    <form:hidden path="comments"/>
    <form:hidden path="announcments"/>
    <form:hidden path="questions"/>
    <form:hidden path="participated"/>
    <form:hidden path="creator"/>
    <form:hidden path="parentRendezvous"/>




    <acme:textbox path="name" code="rendezvous.name"/>
    <acme:textarea path="description" code="rendezvous.description" />
    <acme:textbox path="moment" code="rendezvous.moment" />
    <acme:textbox path="picture" code="rendezvous.picture"/>
    <acme:checkbox path="finalMode" code="rendezvous.finalMode"/>
    <acme:checkbox path="forAdults" code="rendezvous.forAdults"/>

<jstl:if test="${rendezvous.finalMode == false }">
    <acme:submit name="save" code="rendezvous.save"/>
    <jstl:if test="${rendezvous.id !=0 }">
        <acme:submit name="delete" code="rendezvous.delete"/>
    </jstl:if>
</jstl:if>

    <acme:cancel code="rendezvous.cancel" url="creator/rendezvous/list.do"/>

</form:form>