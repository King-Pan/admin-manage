package club.javalearn.admin.service.impl;

import club.javalearn.admin.model.Permission;
import club.javalearn.admin.parameter.PermissionInfo;
import club.javalearn.admin.repository.PermissionRepository;
import club.javalearn.admin.service.PermissionService;
import club.javalearn.admin.utils.Constants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author king-pan
 * Date: 2018/7/12
 * Time: 下午3:35
 * Description: No Description
 */
@Service
public class PermissionServiceImpl implements PermissionService {



    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> getList() {
        return getList("", Constants.NOT_SELECT);
    }

    @Override
    public List<Permission> getList(Long userId) {
        return permissionRepository.getListByUserId(userId);
    }

    @Override
    public List<Permission> getList(String permissionName, String status) {
        Sort sort = new Sort(Sort.Direction.ASC, "orderNum");
        return permissionRepository.findAll(new Specification<Permission>() {
            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> permissionNamePath = root.get("permissionName");
                Path<String> statusPath = root.get("status");
                List<Predicate> wherePredicate = new ArrayList<>();
                if (StringUtils.isNoneBlank(permissionName)) {
                    wherePredicate.add(cb.like(permissionNamePath, "%" + permissionName + "%"));
                }
                if (StringUtils.isNoneBlank(status) && !Constants.NOT_SELECT.equals(status)) {
                    wherePredicate.add(cb.equal(statusPath, status));
                }
                Predicate[] predicates = new Predicate[]{};
                //这里可以设置任意条查询条件
                if (CollectionUtils.isNotEmpty(wherePredicate)) {
                    query.where(wherePredicate.toArray(predicates));
                }
                return null;
            }
        },sort);
    }

    @Override
    public Permission save(PermissionInfo permissionInfo) {
        Permission result;
        Permission permission = permissionInfo.convertPermission();
        if(permission.getPermissionId()!=null){
            Permission p = permissionRepository.getOne(permission.getPermissionId());
            p.setUpdateTime(new Date());
            p.setIcon(permission.getIcon());
            p.setExpression(permission.getExpression());
            p.setUrl(permission.getUrl());
            p.setDescription(permission.getDescription());
            p.setPermissionType(permission.getPermissionType());
            p.setPermissionName(permission.getPermissionName());
            p.setOrderNum(permission.getOrderNum());
            p.setParentName(permission.getParentName());
            p.setParentId(permission.getParentId());
            p.setStatus(permission.getStatus());
            result = permissionRepository.save(p);
        }else{
            permission.setCreateTime(new Date());
            permission.setStatus(Constants.DEFAULT_STATUS);
            result = permissionRepository.save(permission);
        }
        return result;
    }

    @Override
    public int countChildNum(Long parentId) {
        return permissionRepository.countByParentId(parentId);
    }

    @Override
    public Permission get(Long permissionId) {
        return permissionRepository.findByPermissionId(permissionId);
    }

    @Override
    public void updateStatus(List<Long> permissionIds,String status) {
        permissionRepository.updateStatus(permissionIds,status);
    }
}
