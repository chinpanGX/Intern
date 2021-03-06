#pragma once
//cube.h

#include "gameobject.h"

//最初の一回だけmodelを読み込んで2度目以降はそれをloadして配置する

class CCube : public CGameObject
{
private:
	static inline class CModel* m_Model = NULL;
public:
	CCube() {}
	~CCube() {}

	void Init()override;
	void Uninit()override;
	void Update()override;
	void Draw()override;

	static void Load();
	static void Unload();
};