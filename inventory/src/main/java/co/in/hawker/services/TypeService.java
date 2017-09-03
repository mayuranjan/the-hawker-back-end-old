package co.in.hawker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.in.hawker.entities.Type;
import co.in.hawker.repositories.TypeRepository;
import co.in.hawker.services.validations.TypeValidations;

@Service
@Transactional
public class TypeService extends TypeValidations {
	@Autowired
	private TypeRepository typeRespository;

	public Type addType(Type type) {

		// null check
		handleException(checkIfTypeNameIsNotNull(type));
		handleException(checkIfTypeCodeIsNotNull(type));

		// duplicate check
		handleException(checkIfTypeCodeDoesntExists(type));
		handleException(checkIfTypeIdDoesntExists(type));

		return typeRespository.save(type);
	}

	public List<Type> getAllTypes() {
		return typeRespository.findAll();
	}

	public Type getTypeByTypeCode(String typeCode) {
		Type type = new Type();
		type.setTypeCode(typeCode);

		handleException(checkIfTypeCodeDoesntExists(type));
		return typeRespository.findByTypeCode(typeCode);
	}

	public Type getTypeByTypeName(String typeName) {
		Type type = new Type();
		type.setTypeName(typeName);

		return typeRespository.findByTypeName(typeName);
	}

	public Type getTypeByTypeId(Long typeId) {
		Type type = new Type();
		type.setTypeId(typeId);

		handleException(checkIfTypeIdDoesntExists(type));
		return typeRespository.findByTypeId(typeId);
	}

	public Type getTypeByTypeCategory(String typeCategory) {
		Type type = new Type();
		type.setTypeCategory(typeCategory);

		return typeRespository.findByTypeCategory(typeCategory);
	}

	public Type updateTypeByTypeCode(Type type) {
		handleException(checkIfTypeCodeDoesntExists(type));
		type.setTypeId(this.getTypeByTypeCode(type.getTypeCode()).getTypeId());

		return typeRespository.save(type);
	}

	public Type updateTypeByTypeName(Type type) {
		type.setTypeId(this.getTypeByTypeName(type.getTypeName()).getTypeId());

		return typeRespository.save(type);
	}

	public Type updateTypeByTypeId(Type type) {

		handleException(checkIfTypeIdDoesntExists(type));
		return typeRespository.save(type);
	}

	public Type updateTypeByTypeCategory(Type type) {
		type.setTypeId(this.getTypeByTypeCategory(type.getTypeCategory()).getTypeId());

		return typeRespository.save(type);
	}

	public void deleteTypeByTypeCode(Type type) {
		handleException(checkIfTypeCodeDoesntExists(type));
		type.setTypeId(this.getTypeByTypeCode(type.getTypeCode()).getTypeId());
		typeRespository.delete(type);

		return;
	}

	public void deleteTypeByTypeName(Type type) {
		handleException(checkIfTypeIdDoesntExists(type));
		type.setTypeId(this.getTypeByTypeName(type.getTypeName()).getTypeId());
		typeRespository.delete(type);

		return;
	}

	public void deleteTypeByTypeId(Type type) {
		typeRespository.delete(type);

		return;
	}

	public void deleteTypeByTypeCategory(Type type) {
		type.setTypeId(this.getTypeByTypeCategory(type.getTypeCategory()).getTypeId());
		typeRespository.delete(type);

		return;
	}
}