<%@ include file="IncludeTop.jsp"%>

<div align="center">
  <p>
    <font size="4"><b>My Orders</b></font>
  </p>
  <table class="n23">
    <tr bgcolor="#CCCCCC">
      <td><b>ID</b></td> <td><b>name</b></td> <td><b>register date</b></td> 
    </tr>
    <c:forEach var="order" items="${teacherList}">
      <tr bgcolor="#FFFF88">
        <td>
          <b><a href='<c:url value="/shop/viewOrder.do">
              <c:param name="orderId" value="${order.teacher_id}"/></c:url>'>
              <font color="black"><c:out value="${order.teacher_id}" /></font>
            </a></b></td>
        <td>
          <b><a href='<c:url value="/shop/viewOrder.do">
              <c:param name="orderId" value="${order.teacher_name}"/></c:url>'>
              <font color="black"><c:out value="${order.teacher_name}" /></font>
            </a></b></td>
        <td><fmt:formatDate value="${order.register_date}"
            pattern="yyyy/MM/dd hh:mm:ss" /></td>
      </tr>
    </c:forEach>
  </table>
</div>

<%@ include file="IncludeBottom.jsp"%>
