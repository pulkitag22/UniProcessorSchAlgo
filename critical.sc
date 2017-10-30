--!TRACE 

start_section :
		i : integer;
		to_run : integer;
		min_period : integer;
		flag : integer;
end section;

priority_section :
	
		min_period := integer'last;
		flag := 0;
		
		for i in tasks_range loop
			if (tasks.critical(i) = true) then
				if ((tasks.ready(i) = true) and (tasks.rest_of_capacity(i) > 0)) then
					if min_period > tasks.period(i) then
						flag := 1;
						to_run := i;
						min_period := tasks.period(i);
					end if;
				end if;
			end if;
		end loop;
			
		for i in tasks_range loop
			if (tasks.critical(i) = false) and (flag = 0)then
				if ((tasks.ready(i) = true) and (tasks.rest_of_capacity(i) > 0)) then
					if min_period > tasks.period(i) then
						flag := 1;
						to_run := i;
						min_period := tasks.period(i);
					end if;
				end if;
			end if;
		end loop;
		
end section;

election_section :
		return to_run;
end section;