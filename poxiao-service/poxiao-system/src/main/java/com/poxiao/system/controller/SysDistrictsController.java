package com.poxiao.system.controller;

import com.poxiao.api.system.model.Districts;
import com.poxiao.common.auth.annotation.HasPermissions;
import com.poxiao.common.core.controller.BaseController;
import com.poxiao.common.core.model.vo.R;
import com.poxiao.common.core.utils.poi.ExcelUtil;
import com.poxiao.common.log.annotation.OperLog;
import com.poxiao.common.log.enums.BusinessType;
import com.poxiao.system.service.IDistrictsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 地区 信息操作处理
 * 
 * @author ruoyi
 * @date 2018-12-19
 */
@RestController
@RequestMapping("districts")
public class SysDistrictsController extends BaseController
{
    @Autowired
    private IDistrictsService districtsService;

    /**
     * 查询地区列表
     */
    @HasPermissions("system:districts:list")
    @RequestMapping("/list")
    public R list(Districts districts)
    {
        startPage();
        return result(districtsService.selectDistrictsList(districts));
    }

    /**
     * 导出地区列表
     */
    @HasPermissions("system:districts:export")
    @OperLog(title = "地区", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(Districts districts)
    {
        List<Districts> list = districtsService.selectDistrictsList(districts);
        ExcelUtil<Districts> util = new ExcelUtil<Districts>(Districts.class);
        return util.exportExcel(list, "districts");
    }

    /**
     * 新增保存地区
     */
    @HasPermissions("system:districts:add")
    @OperLog(title = "地区", businessType = BusinessType.INSERT)
    @PostMapping("save")
    public R addSave(@RequestBody Districts districts)
    {
        districts.setPid(districts.getId() / 100);
        districts.setCreateTime(new Date());
        districts.setUpdateTime(new Date());
        districts.setOperator(getLoginName());
        return toAjax(districtsService.insertDistricts(districts));
    }

    /**
    
    /**
     * 修改保存地区
     */
    @HasPermissions("system:districts:edit")
    @OperLog(title = "地区", businessType = BusinessType.UPDATE)
    @PostMapping("update")
    public R editSave(@RequestBody Districts districts)
    {
        districts.setPid(districts.getId() / 100);
        districts.setOperator(getLoginName());
        districts.setUpdateTime(new Date());
        return toAjax(districtsService.updateDistricts(districts));
    }

    /**
     * 删除地区
     */
    @HasPermissions("system:districts:remove")
    @OperLog(title = "地区", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R remove(String ids)
    {
        return toAjax(districtsService.deleteDistrictsByIds(ids));
    }
}