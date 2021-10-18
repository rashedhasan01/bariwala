package com.bariwala.bariwala.utilities;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class ReflexionHelper {

    public  String getDeclaredFieldValue(Object request, String fieldName)
    {
        try {
            Field  field =  request.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(request).toString();
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex)
        {
            return "";
        }

    }

    public boolean checkforEmptyFields(Object request)
    {

        for (Field field: request.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            try{
              if (field.get(request)== null || field.get(request)== "" )
              {
                  return true;
              }
            }
            catch (IllegalArgumentException | IllegalAccessException ex)
            {
                return true;
            }
        }

        for (Field field: request.getClass().getSuperclass().getDeclaredFields())
        {
            field.setAccessible(true);
            try{
                if (field.get(request)== null || field.get(request)== "" )
                {
                    return true;
                }
            }
            catch (IllegalArgumentException | IllegalAccessException ex)
            {
                return true;
            }
        }

        return false;
    }
}
