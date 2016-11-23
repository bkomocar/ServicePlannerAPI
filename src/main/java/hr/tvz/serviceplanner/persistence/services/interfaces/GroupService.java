package hr.tvz.serviceplanner.persistence.services.interfaces;

import java.util.List;

import hr.tvz.serviceplanner.dtos.CategoryDto;
import hr.tvz.serviceplanner.dtos.DtoType;
import hr.tvz.serviceplanner.dtos.GroupDto;
import hr.tvz.serviceplanner.dtos.ProductDto;
import hr.tvz.serviceplanner.dtos.request.CreateGroupDto;
import hr.tvz.serviceplanner.dtos.request.UpdateGroupDto;
import hr.tvz.serviceplanner.dtos.response.IdDto;
import hr.tvz.serviceplanner.dtos.response.TimespanEventDto;
import hr.tvz.serviceplanner.persistence.dao.common.Operations;
import hr.tvz.serviceplanner.persistence.models.Group;

public interface GroupService extends Operations<Group> {

	public GroupDto getGroup(Long id, DtoType type);

	public List<CategoryDto> getCategoriesForGroup(Long id, Long groupId, DtoType type);
	
	public List<ProductDto> getProductsForGroup(Long id, Long groupId, DtoType type);
	
	public IdDto createGroup(Long id, CreateGroupDto model);

	public boolean updateGroup(Long id, UpdateGroupDto model);

	public List<TimespanEventDto> getTimeEventsForGroupByDate(long id, long groupId, String date);
}
