package co.in.hawker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.in.hawker.entities.Type;

/**
 * @author Siva
 * 
 */
public interface TypeRepository extends JpaRepository<Type, Integer> {
	Type findByTypeCode(String typeCode);

	Type findByTypeCategory(String typeCategory);

	Type findByTypeName(String typeName);

	Type findByTypeId(Long typeId);
}