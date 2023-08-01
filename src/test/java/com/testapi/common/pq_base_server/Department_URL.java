package com.testapi.common.pq_base_server;

/**
 * @author: chenjiafeng
 * @description 部门信息
 * @Date: 2023/7/28 15:40
 */
public class Department_URL {

    //    查询部门列表（排除节点）
    String exclude_deptId = "/system/dept/list/exclude/{deptId}";

    //根据部门编号获取详细信息
    String dept = "/system/dept/{deptId}";

    //    获取部门列表
    String dept_list = "/system/dept/list";

    //    删除部门
    String dept_deptId = "/dept/{deptId}";
    //新增部门/修改部门

    String system_dept = "/system/dept";
}
