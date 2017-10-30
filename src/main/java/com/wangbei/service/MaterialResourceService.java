package com.wangbei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangbei.dao.BuddhistTopicDao;
import com.wangbei.dao.MaterialResourceDao;
import com.wangbei.entity.BuddhistTopic;
import com.wangbei.entity.MaterialResource;
import com.wangbei.util.enums.FileTypeEnum;
import com.wangbei.util.enums.MaterialResourceTypeEnum;

/**
 * 素材资源 Service
 * 
 * @author luomengan
 *
 */
@Service
public class MaterialResourceService {

	@Autowired
	private MaterialResourceDao materialResourceDao;
	@Autowired
	private BuddhistTopicDao buddhistTopicDao;

	public MaterialResource getMaterialResourceInfo(Integer id) {
		return materialResourceDao.retrieveMaterialResourceById(id);
	}

	@Transactional
	public MaterialResource addMaterialResource(MaterialResource materialResource) {
		String link = materialResource.getLink();
		if (materialResource.getFileType() == FileTypeEnum.Picture) {
			if (link != null && link.indexOf("<img src=") >= 0) {
				// 表示当前为富文件编辑器添加的，对link进行处理
				int imgIndex = link.indexOf("<img src=");
				int titleIndex = link.indexOf("title=");
				if (imgIndex > 0 && titleIndex > 0) {
					link = link.substring(imgIndex + 10, titleIndex - 2);
					materialResource.setLink(link);
				}
			}
		} else if (materialResource.getFileType() == FileTypeEnum.Attachment) {
			if (link != null && link.indexOf("href=") >= 0) {
				// 表示当前为富文件编辑器添加的，对link进行处理
				int hrefIndex = link.indexOf("href=");
				int titleIndex = link.indexOf("title=");
				if (hrefIndex > 0 && titleIndex > 0) {
					link = link.substring(hrefIndex + 6, titleIndex - 2);
					materialResource.setLink(link);
				}
			}
		}
		String smallLink = materialResource.getSmallLink();
		if (smallLink != null && smallLink.indexOf("<img src=") >= 0) {
			// 表示当前为富文件编辑器添加的，对link进行处理
			int imgIndex = smallLink.indexOf("<img src=");
			int titleIndex = smallLink.indexOf("title=");
			if (imgIndex > 0 && titleIndex > 0) {
				smallLink = smallLink.substring(imgIndex + 10, titleIndex - 2);
				materialResource.setSmallLink(smallLink);
			}
		}
		return materialResourceDao.createMaterialResource(materialResource);
	}

	@Transactional
	public MaterialResource modifyMaterialResource(MaterialResource materialResource) {
		String link = materialResource.getLink();
		if (materialResource.getFileType() == FileTypeEnum.Picture) {
			if (link != null && link.indexOf("<img src=") >= 0) {
				// 表示当前为富文件编辑器添加的，对link进行处理
				int imgIndex = link.indexOf("<img src=");
				int titleIndex = link.indexOf("title=");
				if (imgIndex > 0 && titleIndex > 0) {
					link = link.substring(imgIndex + 10, titleIndex - 2);
					materialResource.setLink(link);
				}
			}
		} else if (materialResource.getFileType() == FileTypeEnum.Attachment) {
			if (link != null && link.indexOf("href=") >= 0) {
				// 表示当前为富文件编辑器添加的，对link进行处理
				int hrefIndex = link.indexOf("href=");
				int titleIndex = link.indexOf("title=");
				if (hrefIndex > 0 && titleIndex > 0) {
					link = link.substring(hrefIndex + 6, titleIndex - 2);
					materialResource.setLink(link);
				}
			}
		}
		String smallLink = materialResource.getSmallLink();
		if (smallLink != null && smallLink.indexOf("<img src=") >= 0) {
			// 表示当前为富文件编辑器添加的，对link进行处理
			int imgIndex = smallLink.indexOf("<img src=");
			int titleIndex = smallLink.indexOf("title=");
			if (imgIndex > 0 && titleIndex > 0) {
				smallLink = smallLink.substring(imgIndex + 10, titleIndex - 2);
				materialResource.setSmallLink(smallLink);
			}
		}

		// 修改相关联资源的图片
		if (materialResource.getIsCascadeUpdate()) {
			List<BuddhistTopic> topicList = buddhistTopicDao
					.listBuddhistTopicByMaterialResourceId(materialResource.getId());
			if (topicList != null && topicList.size() > 0) {
				for (BuddhistTopic topic : topicList) {
					topic.setLink(materialResource.getLink());
					topic.setSmallLink(materialResource.getSmallLink());
					buddhistTopicDao.updateBuddhistTopic(topic);
				}
			}
		}
		return materialResourceDao.updateMaterialResource(materialResource);
	}

	@Transactional
	public void deleteMaterialResource(Integer id) {
		materialResourceDao.deleteMaterialResourceById(id);
	}

	@Transactional
	public void deleteMaterialResources(String ids) {
		if (ids != null) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				if (!"".equals(id.trim())) {
					materialResourceDao.deleteMaterialResourceById(Integer.parseInt(id.trim()));
				}
			}
		}
	}

	public Page<MaterialResource> materialResources(int page, int limit) {
		return materialResourceDao.pageMaterialResource(page, limit);
	}

	public List<MaterialResource> list() {
		return materialResourceDao.listMaterialResource();
	}

	public List<MaterialResource> listByType(MaterialResourceTypeEnum type) {
		return materialResourceDao.listMaterialResourceByType(type);
	}

}
