/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package glslformatter;

/**
 *
 * @author LWJGL2
 */
public class GLSLFormatter {

    public static void main(String[] args) throws Exception {
        String result = Beautifier.beautify("#version 120\n" +
                "#version 120\n" +
                "\n" +
                "#define MAX_DIR_LIGHTS 10\n" +
                "#define MAX_POINT_LIGHTS 10\n" +
                "\n" +
                "struct DirLight {\n" +
                "    vec3 direction;\n" +
                "\n" +
                "    vec3 position;\n" +
                "    vec3 ambient;\n" +
                "    vec3 diffuse;\n" +
                "    vec3 specular;\n" +
                "    bool renderShadow;\n" +
                "\n" +
                "    sampler2DShadow depthMap;\n" +
                "};\n" +
                "\n" +
                "struct PointLight {\n" +
                "    vec3 position;\n" +
                "\n" +
                "    vec3 ambient;\n" +
                "    vec3 diffuse;\n" +
                "    vec3 specular;\n" +
                "\n" +
                "    float constant;\n" +
                "    float linear;\n" +
                "    float quadratic;\n" +
                "};\n" +
                "\n" +
                "struct Material {\n" +
                "    vec4 diffuseColor;\n" +
                "    vec3 sagColor;\n" +
                "\n" +
                "    sampler2D diffuse;\n" +
                "    sampler2D normal;\n" +
                "    sampler2D sag;\n" +
                "\n" +
                "    samplerCube skybox;\n" +
                "\n" +
                "    float shininess;\n" +
                "};\n" +
                "\n" +
                "varying vec3 vPos;\n" +
                "varying vec3 vNormal;\n" +
                "varying vec2 vTextCoords;\n" +
                "varying vec4 vColor;\n" +
                "varying mat3 vTBN;\n" +
                "varying vec4 [10] vFragPosLightSpace;\n" +
                "varying float visibility;\n" +
                "\n" +
                "// fog\n" +
                "uniform vec3 fogColor = vec3(0.7, 0.8, 1);\n" +
                "\n" +
                "uniform int dirLightsCount;\n" +
                "uniform DirLight dirLights[MAX_DIR_LIGHTS];\n" +
                "uniform int pointLightsCount;\n" +
                "uniform PointLight pointLights[MAX_POINT_LIGHTS];\n" +
                "\n" +
                "uniform vec3 viewPos;\n" +
                "\n" +
                "uniform Material material;\n" +
                "uniform sampler2D noiseTexture;\n" +
                "\n" +
                "uniform int shadowResolution;\n" +
                "\n" +
                "float sampleShadowMap(sampler2DShadow shadowMap, vec3 lpos);\n" +
                "float sampleShadowMap(sampler2DShadow shadowMap, vec3 shadow_tc, vec2 noise, vec2 shadowmap_texel);\n" +
                "float calcShadowAmount(int index, DirLight light, vec3 normal, vec3 projCoords);\n" +
                "vec3 calcDirectionLight(int index, DirLight light, vec3 normal, vec3 fragPos, vec3 viewDir, vec3 color, vec3 sag, vec3 skyColor, vec3 skyReflection);\n" +
                "vec3 calcPointLight(PointLight light, vec3 normal, vec3 fragPos, vec3 viewDir, vec3 color, vec3 sag, vec3 skyColor, vec3 skyReflection);\n" +
                " \n" +
                "void main() {\n" +
                " \n" +
                "\n" +
                "    vec3 result = vec3(vColor.rgb);\n" +
                " \n" +
                "\n" +
                "    vec2 texCoords = vTextCoords;\n" +
                "    vec4 diffuse = texture2D(material.diffuse, texCoords); \n" +
                "    \n" +
                "    //vec3 normal = vec3(0.19);//(texture2D(material.normal, texCoords).rgb * 2.0 - 1.0);\n" +
                "    \n" +
                "    //normal = normalize(Normal);\n" +
                "    //normal = TBN;\n" +
                "\n" +
                "    vec3 normal = texture2D(material.normal, texCoords).rgb * 2.0 - 1.0;\n" +
                "\n" +
                "    if(normal == vec3(0.0)) {\n" +
                "\n" +
                "    } else {\n" +
                "        //normal = normalize(TBN * normal);\n" +
                "    }\n" +
                "	if(normal == vec3(0.0)) {\n" +
                "\n" +
                "    } else {\n" +
                "        //normal = normalize(TBN * normal);\n" +
                "    }\n" +
                "	if(normal == vec3(0.0)) {\n" +
                "\n" +
                "    } else {\n" +
                "        //normal = normalize(TBN * normal);\n" +
                "    }\n" +
                "	if(normal == vec3(0.0)) {\n" +
                "\n" +
                "    } else {\n" +
                "        //normal = normalize(TBN * normal);\n" +
                "    }\n" +
                "	if(normal == vec3(0.0)) {\n" +
                "\n" +
                "    } else {\n" +
                "        //normal = normalize(TBN * normal);\n" +
                "    }if(normal == vec3(0.0)) {\n" +
                "\n" +
                "    } else {\n" +
                "        //normal = normalize(TBN * normal);\n" +
                "    }\n" +
                "    normal = normalize(vTBN * vec3(1));\n" +
                "\n" +
                "    if(diffuse.rgb != vec3(0)) {\n" +
                "        result *= diffuse.rgb;\n" +
                "    }\n" +
                "\n" +
                "    vec3 skyColor = textureCube(material.skybox, normal).rgb;\n" +
                "    vec3 sag = texture2D(material.sag, texCoords).xyz; //* material.sagColor;\n" +
                "\n" +
                "    float ratio = 1.00 / 1.52;\n" +
                "    vec3 I = normalize(vPos - viewPos);\n" +
                "    vec3 R = refract(I, normal, ratio);\n" +
                "    vec3 skyReflection = textureCube(material.skybox, R).rgb;\n" +
                "\n" +
                "    vec3 viewDir = normalize(viewPos - vPos);\n" +
                "\n" +
                "    vec3 finalResult = vec3(0); for(int i = 0; i < dirLightsCount; i++) {\n" +
                "\n" +
                "        finalResult += calcDirectionLight(i, dirLights[0], normal, vPos, viewDir, result.xyz, vec3(0), skyColor, skyReflection);\n" +
                "    }\n" +
                "    \n" +
                "    finalResult = mix(fogColor, finalResult, visibility);\n" +
                "    gl_FragData[0] = vec4(finalResult, 1);//vec4(result, 1);//vec4((color * (1 - shadowFactor)).rgb, 1);\n" +
                "    gl_FragData[1] = gl_FragData[0] / 2;\n" +
                " \n" +
                "    // fog\n" +
                "     // vec4(0, 0, 0, 1);//vec4(1, gl_FragData[0].g, 1, 1);\n" +
                "}\n" +
                "\n" +
                "float sampleShadowMap(sampler2DShadow shadowMap, vec3 lpos) {\n" +
                "if(normal == vec3(0.0)) {\n" +
                "\n" +
                "    } else {\n" +
                "        //normal = normalize(TBN * normal);\n" +
                "    }\n" +
                "    float shadow = shadow2D(shadowMap, lpos).x;\n" +
                "    return shadow;\n" +
                "}\n" +
                "float sampleShadowMap(sampler2DShadow shadowMap, vec3 shadow_tc, vec2 noise, vec2 shadowmap_texel) {\n" +
                "    noise = vec2(1.0);\n" +
                "    vec3 dx = vec3(noise.x * shadowmap_texel.x, 0.0, 0.0);\n" +
                "    vec3 dy = vec3(0.0, noise.y * shadowmap_texel.y, 0.0);\n" +
                "    vec3 dxdy_p = (dx + dy);\n" +
                "    vec3 dxdy_n = (dx - dy);\n" +
                "\n" +
                "    float result = shadow2D(shadowMap, shadow_tc + dx).x + shadow2D(shadowMap, shadow_tc - dx).x + shadow2D(shadowMap, shadow_tc + dy).x + shadow2D(shadowMap, shadow_tc - dy).x + shadow2D(shadowMap, shadow_tc + dxdy_p).x + shadow2D(shadowMap, shadow_tc - dxdy_p).x + shadow2D(shadowMap, shadow_tc + dxdy_n).x + shadow2D(shadowMap, shadow_tc - dxdy_n).x;\n" +
                "\n" +
                "    return 0.125 * result;\n" +
                "}\n" +
                "\n" +
                "float calcShadowAmount(int index, DirLight light, vec3 normal, vec3 projCoords) {\n" +
                "    vec2 texCoords = vTextCoords;\n" +
                "\n" +
                "    vec2 texelSize = 1.0 / vec2(shadowResolution);\n" +
                "    vec2 noiseScale = 1.0 / vec2(texCoords);\n" +
                "\n" +
                "    float shadow = 0.0;\n" +
                "    \n" +
                "    \n" +
                "    //vec2 noise = vec2(2.0) * texture2D(noiseTexture, projCoords.xy * noiseScale).xy - vec2(1.0);\n" +
                "\n" +
                "    shadow = sampleShadowMap(light.depthMap, projCoords, texelSize, texelSize);\n" +
                "    shadow = clamp(shadow, 0, 0.5);\n" +
                "\n" +
                "    return - shadow;\n" +
                "}\n" +
                "\n" +
                "vec3 calcDirectionLight(int index, DirLight light, vec3 normal, vec3 fragPos, vec3 viewDir, vec3 color, vec3 sag, vec3 skyColor, vec3 skyReflection) {\n" +
                "    vec3 ambient = light.ambient * color * (skyColor * vec3(0.2)) + color * vec3(0.1);\n" +
                "if(normal == vec3(0.0)) {\n" +
                "\n" +
                "    } else {\n" +
                "        //normal = normalize(TBN * normal);\n" +
                "    }\n" +
                "    vec3 lightDir = normalize(- light.direction);\n" +
                "    float diff = max(dot(lightDir, normal / 2), 0.0) + 0.6;\n" +
                "    vec3 diffuse = diff * color * light.diffuse;\n" +
                "\n" +
                "    vec3 reflectDir = reflect(- lightDir, normal);\n" +
                "    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);\n" +
                "    vec3 specular = vec3(spec * sag.r * light.specular);\n" +
                "    vec3 reflection = skyReflection * diffuse * sag.b;\n" +
                "\n" +
                "    float shadowFactor = 0;\n" +
                "\n" +
                "    \n" +
                "    if(true) {\n" +
                "        vec3 projCoords = vFragPosLightSpace[index].xyz / vFragPosLightSpace[index].w;\n" +
                "        projCoords = projCoords * 0.5 + 0.5;\n" +
                "        shadowFactor = calcShadowAmount(index, light, normal, projCoords);\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    if(shadowFactor > 0.5) {\n" +
                "        shadowFactor = 0.5;\n" +
                "    }\n" +
                "\n" +
                "    return vec3((ambient + diffuse + specular + reflection) * ((1 - shadowFactor) / 1.5));\n" +
                "}\n" +
                "\n" +
                "vec3 calcPointLight(PointLight light, vec3 normal, vec3 fragPos, vec3 viewDir, vec3 color, vec3 sag, vec3 skyColor, vec3 skyReflection) {\n" +
                "    float distance = length(light.position - vPos);\n" +
                "    float attenuation = 1.0 / (light.constant + light.linear * distance + light.quadratic * (distance * distance));\n" +
                "\n" +
                "    vec3 ambient = vec3(0.0);\n" +
                "    vec3 diffuse = vec3(0.0);\n" +
                "    vec3 specular = vec3(0.0);\n" +
                "    vec3 reflection = vec3(0.0);\n" +
                "\n" +
                "    if (attenuation > 0.0) {\n" +
                "        ambient = light.ambient * color * (skyColor * vec3(0.5) + vec3(0.5));\n" +
                "        \n" +
                "        vec3 lightDir = normalize(light.position - vPos);\n" +
                "        float diff = max(dot(lightDir, normal), 0.0);\n" +
                "        diffuse = diff * color * light.diffuse;\n" +
                "\n" +
                "        vec3 reflectDir = reflect(- lightDir, normal);\n" +
                "        vec3 halfwayDir = normalize(lightDir + viewDir);\n" +
                "        float spec = pow(max(dot(normal, halfwayDir), 0.0), material.shininess);\n" +
                "        specular = vec3(spec * sag.r * light.specular);\n" +
                "        reflection = skyReflection * diffuse * sag.b;\n" +
                "       \n" +
                "        ambient *= attenuation;\n" +
                "        diffuse *= attenuation;\n" +
                "        specular *= attenuation;\n" +
                "        reflection *= attenuation;\n" +
                "    }\n" +
                "if(normal == vec3(0.0)) {\n" +
                "\n" +
                "    } else {\n" +
                "        //normal = normalize(TBN * normal);\n" +
                "    }\n" +
                "    return vec3(ambient + diffuse + specular + reflection);\n" +
                "}");
        System.out.println("Result:\n" + result);
    }

}
