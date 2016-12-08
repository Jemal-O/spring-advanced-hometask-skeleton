package ua.epam.spring.hometask.dataManipulation;

import java.util.List;

import ua.epam.spring.hometask.domain.VipSeats;

public interface VipSeatsDao {

	public void save(VipSeats vipSeats);

	public void delete(VipSeats vipSeats);

	public VipSeats getById(Integer id);

	public List<VipSeats> getAll(Integer auditoriumId);

	public void update(VipSeats vipSeats);
}
