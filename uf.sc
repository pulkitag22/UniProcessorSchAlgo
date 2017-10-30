--!TRACE 

start_section :
		i : integer;
		n : integer;
		flag : integer := 0;
		dynamic_priority : array (tasks_range) of integer;
		time_period_multiplication : integer := 1;
end section;

priority_section :
		for n in tasks_range loop 
			if ((tasks.ready(n) = true) and (tasks.rest_of_capacity(n) > 0) and (flag = 0)) then
				time_period_multiplication := time_period_multiplication * tasks.period(n);
			end if;
		end loop;
		
		flag := 1;

		for i in tasks_range loop
			if ((tasks.ready(i) = true) and (tasks.rest_of_capacity(i) > 0)) then 
					dynamic_priority(i) := tasks.capacity(i) * time_period_multiplication / tasks.period(i);
					put (dynamic_priority(i));
			end if;
		end loop;
end section;

election_section :
		return min_to_index (dynamic_priority);
end section;