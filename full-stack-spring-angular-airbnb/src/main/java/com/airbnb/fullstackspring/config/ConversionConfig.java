package com.airbnb.fullstackspring.config;

import com.airbnb.fullstackspring.converter.ReservationEntityToReservationResponseConverter;
import com.airbnb.fullstackspring.converter.ReservationRequestToReservationEntityConverter;
import com.airbnb.fullstackspring.converter.RoomEntityToReservableRoomResponseConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConversionConfig {
    private Set<Converter> getConverters(){
        Set<Converter> converters = new HashSet<>();
        converters.add(new RoomEntityToReservableRoomResponseConverter());
        converters.add(new ReservationRequestToReservationEntityConverter());
        converters.add(new ReservationEntityToReservationResponseConverter());

        return  converters;
    }

    @Bean
    public ConversionService conversionService(){
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(getConverters());
        bean.afterPropertiesSet();

        return bean.getObject();
    }
}
