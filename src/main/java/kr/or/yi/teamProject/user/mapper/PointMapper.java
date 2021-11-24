package kr.or.yi.teamProject.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.yi.teamProject.user.dto.Point;

@Mapper
public interface PointMapper {
	Point selectPoint(Point point);
	
	int insertPoint(Point point);
	int usePoint(Point point);
	
	int updatePoint (Point point);
	int deletePoint (Point point);
	
	List<Point> selectPointList(Point point);
}
