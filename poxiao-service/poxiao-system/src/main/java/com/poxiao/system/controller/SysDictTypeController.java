package com.poxiao.system.controller;

import com.poxiao.api.system.model.SysDictType;
import com.poxiao.common.auth.annotation.HasPermissions;
import com.poxiao.common.core.controller.BaseController;
import com.poxiao.common.core.model.vo.R;
import com.poxiao.common.core.page.PageDomain;
import com.poxiao.common.log.annotation.OperLog;
import com.poxiao.common.log.enums.BusinessType;
import com.poxiao.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 字典类型 提供者
 * 
 * @author zmr
 * @date 2019-05-20
 */
@RestController
@RequestMapping("dict/type")
public class SysDictTypeController extends BaseController
{
	
	@Autowired
	private ISysDictTypeService sysDictTypeService;
	
	/**
	 * 查询字典类型
	 */
	@GetMapping("get/{dictId}")
	public SysDictType get(@PathVariable("dictId") Long dictId)
	{
		return sysDictTypeService.selectDictTypeById(dictId);
		
	}
	
	/**
	 * 查询字典类型列表
	 */
	@GetMapping("list")
	@HasPermissions("system:dict:list")
	public R list(SysDictType sysDictType, PageDomain page)
	{
		startPage();
        return result(sysDictTypeService.selectDictTypeList(sysDictType));
	}
	
	
	/**
	 * 新增保存字典类型
	 */
	@OperLog(title = "字典类型", businessType = BusinessType.INSERT)
    @HasPermissions("system:dict:add")
	@PostMapping("save")
	public R addSave(@RequestBody SysDictType sysDictType)
	{		
		return toAjax(sysDictTypeService.insertDictType(sysDictType));
	}

	/**
	 * 修改保存字典类型
	 */
	@OperLog(title = "字典类型", businessType = BusinessType.UPDATE)
    @HasPermissions("system:dict:edit")
	@PostMapping("update")
	public R editSave(@RequestBody SysDictType sysDictType)
	{		
		return toAjax(sysDictTypeService.updateDictType(sysDictType));
	}
	
	/**
	 * 删除字典类型
	 * @throws Exception 
	 */
	@OperLog(title = "字典类型", businessType = BusinessType.DELETE)
	@HasPermissions("system:dict:remove")
	@PostMapping("remove")
	public R remove(String ids) throws Exception
	{		
		return toAjax(sysDictTypeService.deleteDictTypeByIds(ids));
	}
	
}
