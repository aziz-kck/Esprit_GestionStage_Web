package com.example.pidev.Mapper;

import com.example.pidev.DAO.Entities.*;

import com.example.pidev.DAO.Repositories.UserRepositories;




import com.example.pidev.Service.Classe.Security;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;





@Mapper(componentModel = "spring")
public abstract class PostMapper {




   }












